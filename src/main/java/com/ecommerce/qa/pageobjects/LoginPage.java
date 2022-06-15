package com.ecommerce.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ecommerce.qa.utils.TestDataProvider.envConfig;

public class LoginPage extends HomePage {

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

    @FindBy(css = "div[class*='alert-danger']")
    private WebElement alertPopUp;


    public AccountPage login() {
        emailField.sendKeys(envConfig.getUserEmail());
        passwordField.sendKeys(envConfig.getUserPassword());
        clickElement(signInButton);
        return new AccountPage();
    }

    public String login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        clickElement(signInButton);
        return alertPopUp.getText();
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        clickElement(forgotPasswordButton);
        return new ForgotPasswordPage();
    }
}
