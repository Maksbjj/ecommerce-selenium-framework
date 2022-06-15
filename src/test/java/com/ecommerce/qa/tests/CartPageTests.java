package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.*;
import com.ecommerce.qa.pageobjects.cartpages.CartAddressPage;
import com.ecommerce.qa.pageobjects.cartpages.CartSummaryPage;
import com.ecommerce.qa.utils.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.ecommerce.qa.utils.TestDataProvider.alerts;
import static com.ecommerce.qa.utils.TestDataProvider.pageTitles;


@Listeners({AllureListener.class})
public class CartPageTests extends BaseTest {

    @Test(description = "Open cart as not logged in user")
    @Description("Verify if empty cart message is shown to not logged in user after opening the cart")
    @Feature("Cart")
    @Severity(SeverityLevel.MINOR)
    public void shouldShowAlertForEmptyCart(){
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().openCartPage();
        String alertMessage = cartPage.getElementsText(basePage.getAlertMessage());
        Assert.assertEquals(alertMessage,alerts.getCartEmpty());
    }

    @Test(description = "Delete product from the cart")
    @Description("Verify if product is deleted from the cart after clicking on delete button")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDeleteProductFromTheCart(){
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage().deleteProductFromCart();
        waitForElementToBeDisplayed(basePage.getAlertMessage());
        String alertMessage = cartPage.getElementsText(cartPage.getAlertMessage());
        Assert.assertEquals(alertMessage,alerts.getCartEmpty());
    }

    @Test(description = "Increase the number of particular product in the cart")
    @Description("Verify if number of particular product in the cart increased")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldIncreaseProductQuantityInTheCart(){
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage();
        int productQuantityBefore = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.increaseProductQuantity();
        waitUntilJqueryIsDone();
        int productQuantityAfter = Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(productQuantityAfter,productQuantityBefore + 1);
    }

    @Test(description = "Decrease the number of particular product in the cart")
    @Description("Verify if number of particular product in the cart decreased")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDecreaseProductQuantityInTheCart(){
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage();
        cartPage.increaseProductQuantity();
        waitUntilJqueryIsDone();
        int increasedQuantity = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.decreaseProductQuantity();
        waitUntilJqueryIsDone();
        int productQuantityDecreased = Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(productQuantityDecreased, increasedQuantity - 1);
    }

    @Test(description = "Proceed to the next step from the cart as not logged in user")
    @Description("Verify if not logged in user is redirected to login page after proceeding with order")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectNotLoggedInUserToLoginPage(){
        HomePage basePage = new HomePage();
        LoginPage loginPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage().proceedToLoginPage();
        Assert.assertEquals(pageTitles.getLogin(), loginPage.getPageTitle());
    }

    @Test(description = "Proceed to the next step from the cart as logged in user")
    @Description("Verify if logged in user is redirected to address page after proceeding with order")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectLoggedInUserToAddressPage() {
        HomePage basePage = new HomePage();
        AccountPage login = basePage.openHomePage().openLoginPage().login();
        CartAddressPage cartAddressPage = login.openHomePage()
                .addProductToCart().proceedToTheCartPage().proceedToAddressPage();
        Assert.assertEquals(cartAddressPage.getPageTitle(),pageTitles.getOrder());
    }
}
