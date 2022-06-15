package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.AccountPage;
import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.ForgotPasswordPage;
import com.ecommerce.qa.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.qa.utils.TestDataProvider.pageTitles;

public class LoginPageTests extends BaseTest {

    @Test
    public void shouldLoginIntoAccount() {
        HomePage basePage = new HomePage();
        AccountPage accountPage = basePage.openHomePage().openLoginPage().login();
        String pageTitle = accountPage.getPageTitle();
        Assert.assertEquals(pageTitle, pageTitles.getAccount());
    }

    @Test(dataProvider = "failed_login_data", dataProviderClass = TestDataProvider.class)
    public void shouldFailLoggingIntoAccount(String email, String password, String alert) {
        HomePage basePage = new HomePage();
        String alertMessage = basePage.openHomePage().openLoginPage().login(email, password);
        Assert.assertTrue(alertMessage.contains(alert));
    }

    @Test
    public void shouldRedirectToForgotPasswordPage() {
        HomePage basePage = new HomePage();
        ForgotPasswordPage forgotPassword = basePage.openHomePage().openLoginPage().goToForgotPasswordPage();
        String pageTitle = forgotPassword.getPageTitle();
        Assert.assertEquals(pageTitle, pageTitles.getForgotPassword());
    }
}
