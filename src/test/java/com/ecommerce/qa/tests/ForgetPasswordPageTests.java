package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ForgotPasswordPage;
import com.ecommerce.qa.pages.LoginPage;
import com.ecommerce.qa.util.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.qa.util.CsvUtil.getPageTitle;

public class ForgetPasswordPageTests extends BaseTest {

    @Test(dataProvider = "forgot_password_data", dataProviderClass = TestDataProvider.class)
    public void submitForgotPasswordForm(String email, String message) {
        BasePage basePage = new BasePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        String alertMessage = forgotPasswordPage.submitForgotEmailInput(email);
        Assert.assertEquals(alertMessage, message);
    }

    @Test
    public void goBackToLoginPage() {
        BasePage basePage = new BasePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        LoginPage loginPage = forgotPasswordPage.goBackToLoginPage();
        Assert.assertEquals(loginPage.getPageTitle(), getPageTitle("Account"));
    }

    @Test
    public void goBackToLoginPageByBreadCrumbButton() {
        BasePage basePage = new BasePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        LoginPage loginPage = forgotPasswordPage.goBackToLoginPageByBreadCrumb();
        Assert.assertEquals(loginPage.getPageTitle(), getPageTitle("Account"));
    }


}
