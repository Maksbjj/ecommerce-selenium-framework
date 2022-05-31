package com.ecommerce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(){
        super();
    }

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(css = "button[class*='button-medium']")
    private WebElement submitInput;

    @FindBy(css ="a[title='Back to Login']")
    private WebElement backToLoginPageButton;

    @FindBy(css = "div[class*='alert']")
    private WebElement alert;

    @FindBy(css = "a[title='Authentication']")
    private WebElement breadCrumbGoBackToLoginButton;


    public String submitForgotEmailInput(String email){
        emailInput.sendKeys(email);
        submitInput.click();
        wait.until(ExpectedConditions.visibilityOf(alert));
        return alert.getText();
    }

    public LoginPage goBackToLoginPage(){
        backToLoginPageButton.click();
        return new LoginPage();
    }

    public LoginPage goBackToLoginPageByBreadCrumb(){
        breadCrumbGoBackToLoginButton.click();
        return new LoginPage();
    }
}
