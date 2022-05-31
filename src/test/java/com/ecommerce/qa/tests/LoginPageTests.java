package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.AccountPage;
import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ForgotPasswordPage;
import com.ecommerce.qa.util.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTests extends BaseTest {


    @Test
    public void shouldLoginIntoAccount() {
        BasePage basePage = new BasePage();
        AccountPage accountPage = basePage.openHomePage().openLoginPage().login();
        String pageTitle = accountPage.getPageTitle();
        Assert.assertEquals(pageTitle, getPageTitle("Account"));
    }

    @Test(dataProvider = "failed_login_data",dataProviderClass = TestDataProvider.class)
    public void shouldFailLoggingIntoAccount(String email,String password,String alert){
        BasePage basePage = new BasePage();
        String alertMessage = basePage.openHomePage().openLoginPage().login(email, password);
        Assert.assertTrue(alertMessage.contains(alert));
    }

    @Test
    public void shouldRedirectToForgotPasswordPage() {
        BasePage basePage = new BasePage();
        ForgotPasswordPage forgotPassword = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        String pageTitle = forgotPassword.getPageTitle();
        Assert.assertEquals(pageTitle, getPageTitle("Forgot password"));
    }

}
