package com.ecommerce.qa.pages;

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


    public AccountPage login() {
        emailField.sendKeys("maxim3093@gmail.com");
        passwordField.sendKeys("301093");
        signInButton.click();
        return new AccountPage();
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        forgotPasswordButton.click();
        return new ForgotPasswordPage();
    }
}
