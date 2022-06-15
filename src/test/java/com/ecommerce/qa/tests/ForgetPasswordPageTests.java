package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.ForgotPasswordPage;
import com.ecommerce.qa.pageobjects.LoginPage;
import com.ecommerce.qa.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.qa.utils.TestDataProvider.pageTitles;


public class ForgetPasswordPageTests extends BaseTest {

    @Test(dataProvider = "forgot_password_data", dataProviderClass = TestDataProvider.class)
    public void submitForgotPasswordForm(String email, String message) {
        HomePage basePage = new HomePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        String alertMessage = forgotPasswordPage.submitForgotEmailInput(email);
        Assert.assertTrue(alertMessage.contains(message));
    }

    @Test
    public void goBackToLoginPage() {
        HomePage basePage = new HomePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        LoginPage loginPage = forgotPasswordPage.goBackToLoginPage();
        Assert.assertEquals(loginPage.getPageTitle(), pageTitles.getLogin());
    }

    @Test
    public void goBackToLoginPageByBreadCrumbButton() {
        HomePage basePage = new HomePage();
        ForgotPasswordPage forgotPasswordPage = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        LoginPage loginPage = forgotPasswordPage.goBackToLoginPageByBreadCrumb();
        Assert.assertEquals(loginPage.getPageTitle(), pageTitles.getLogin());
    }

}
