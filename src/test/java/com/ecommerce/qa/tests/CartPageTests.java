package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.*;
import com.ecommerce.qa.pages.cartpages.CartAddressPage;
import com.ecommerce.qa.pages.cartpages.CartSummaryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTests extends BaseTest {

    @Test
    public void shouldShowAlertForEmptyCart(){
        BasePage basePage = new BasePage();
        CartSummaryPage cartPage = basePage.openHomePage().openCartPage();
        String alertMessage = cartPage.getAlertMessage();
        Assert.assertEquals(alertMessage,alerts.getCartEmpty());
    }

    @Test
    public void shouldDeleteProductFromTheCart(){
        BasePage basePage = new BasePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage().deleteProductFromCart();
        wait.until(ExpectedConditions.visibilityOf(basePage.alertMessage));
        String alertMessage = cartPage.getAlertMessage();
        Assert.assertEquals(alertMessage,alerts.getCartEmpty());
    }

    @Test
    public void shouldIncreaseProductQuantityInTheCart(){
        BasePage basePage = new BasePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage();
        int productQuantityBefore = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.increaseProductQuantity();
        wait.until(cartPage.isQuantityIncreasedByOne(productQuantityBefore));
        int productQuantityAfter = Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(productQuantityAfter,productQuantityBefore +1);
    }

    @Test
    public void shouldDecreaseProductQuantityInTheCart(){
        BasePage basePage = new BasePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage();
        int productQuantityBefore = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.increaseProductQuantity();
        wait.until(cartPage.isQuantityIncreasedByOne(productQuantityBefore));
        int increasedQuantity = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.decreaseProductQuantity();
        wait.until(cartPage.isQuantityDecreasedByOne(increasedQuantity));
        int productQuantityDecreased = Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(productQuantityBefore, productQuantityDecreased);
    }

    @Test
    public void shouldRedirectNotLoggedInUserToLoginPage(){
        BasePage basePage = new BasePage();
        LoginPage loginPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage().proceedToLoginPage();
        Assert.assertEquals(pageTitles.getLogin(), loginPage.getPageTitle());
    }

    @Test
    public void shouldRedirectLoggedInUserToAddressPage() {
        BasePage basePage = new BasePage();
        AccountPage login = basePage.openHomePage().openLoginPage().login();
        CartAddressPage cartAddressPage = login.openHomePage()
                .addProductToCart().proceedToTheCartPage().proceedToAddressPage();
        Assert.assertEquals(cartAddressPage.getPageTitle(),pageTitles.getOrder());
    }

}
