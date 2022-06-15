package com.ecommerce.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends HomePage {

    public ForgotPasswordPage(){
        super();
    }

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(css = "button[class*='button-medium']")
    private WebElement submitInput;

    @FindBy(css ="a[title='Back to Login']")
    private WebElement backToLoginPageButton;


    @FindBy(css = "a[title='Authentication']")
    private WebElement breadCrumbGoBackToLoginButton;


    public String submitForgotEmailInput(String email){
        emailInput.sendKeys(email);
        clickElement(submitInput);
        waitForElementToBeDisplayed(getAlertMessage());
        return getElementsText(getAlertMessage());
    }

    public LoginPage goBackToLoginPage(){
        clickElement(backToLoginPageButton);
        return new LoginPage();
    }

    public LoginPage goBackToLoginPageByBreadCrumb(){
        clickElement(breadCrumbGoBackToLoginButton);
        return new LoginPage();
    }
}
