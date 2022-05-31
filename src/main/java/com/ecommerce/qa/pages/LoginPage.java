package com.ecommerce.qa.pages;

import com.ecommerce.qa.util.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }


    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @FindBy(css = "a[title*='Recover your forgotten password']")
    private WebElement forgotPasswordButton;

    @FindBy(css ="div[class*='alert-danger']")
    private WebElement alertPopUp;


    public AccountPage login() {
        emailField.sendKeys(envConfig.getUserEmail());
        passwordField.sendKeys(envConfig.getUserPassword());
        signInButton.click();
        return new AccountPage();
    }

    public String login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return alertPopUp.getText();
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        forgotPasswordButton.click();
        return new ForgotPasswordPage();
    }
}
