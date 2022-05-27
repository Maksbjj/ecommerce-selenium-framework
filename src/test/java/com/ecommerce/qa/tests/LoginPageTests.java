package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.AccountPage;
import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ForgotPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTests extends BaseTest {


    @Test
    public void loginToAccount() {
        BasePage basePage = new BasePage();
        AccountPage accountPage = basePage.openHomePage().openLoginPage().login();
        String pageTitle = accountPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Account");
    }

    @Test
    public void goToForgotPasswordPage() {
        BasePage basePage = new BasePage();
        ForgotPasswordPage forgotPassword = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        String pageTitle = forgotPassword.getPageTitle();
        Assert.assertEquals(pageTitle, getPageTitle("Forgot password"));
    }

}
