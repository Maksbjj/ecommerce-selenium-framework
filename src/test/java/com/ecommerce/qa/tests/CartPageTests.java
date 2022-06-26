package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.AccountPage;
import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.LoginPage;
import com.ecommerce.qa.pageobjects.cartpages.CartAddressPage;
import com.ecommerce.qa.pageobjects.cartpages.CartSummaryPage;
import com.ecommerce.qa.pageobjects.myaccount.AddNewAddressPage;
import com.ecommerce.qa.pageobjects.myaccount.MyAddressesPage;
import com.ecommerce.qa.utils.AllureListener;
import com.ecommerce.qa.utils.TestDataProvider;
import com.ecommerce.qa.utils.pojo.addressform.CorrectAddress;
import com.ecommerce.qa.utils.pojo.addressform.IncorrectAddress;
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
    public void shouldShowAlertForEmptyCart() {
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().openCartPage();
        waitForElementToBeDisplayed(basePage.getAlertMessage());
        String alertMessage = cartPage.getElementsText(basePage.getAlertMessage());
        Assert.assertEquals(alertMessage, alerts.getCartEmpty());
    }

    @Test(description = "Delete product from the cart")
    @Description("Verify if product is deleted from the cart after clicking on delete button")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDeleteProductFromTheCart() {
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage().deleteProductFromCart();
        waitForElementToBeDisplayed(basePage.getAlertMessage());
        String alertMessage = cartPage.getElementsText(cartPage.getAlertMessage());
        Assert.assertEquals(alertMessage, alerts.getCartEmpty());
    }

    @Test(description = "Increase the number of particular product in the cart")
    @Description("Verify if number of particular product in the cart increased")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldIncreaseProductQuantityInTheCart() {
        HomePage basePage = new HomePage();
        CartSummaryPage cartPage = basePage.openHomePage().addProductToCart().proceedToTheCartPage();
        int productQuantityBefore = Integer.parseInt(cartPage.getProductQuantity());
        cartPage.increaseProductQuantity();
        waitUntilJqueryIsDone();
        int productQuantityAfter = Integer.parseInt(cartPage.getProductQuantity());
        Assert.assertEquals(productQuantityAfter, productQuantityBefore + 1);
    }

    @Test(description = "Decrease the number of particular product in the cart")
    @Description("Verify if number of particular product in the cart decreased")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDecreaseProductQuantityInTheCart() {
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
    public void shouldRedirectNotLoggedInUserToLoginPage() {
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
        Assert.assertEquals(cartAddressPage.getPageTitle(), pageTitles.getOrder());
    }

    @Test(description = "Choose previously created address as logged in user")
    @Description("Verify if logged in user is able to choose delivery address from the list")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldChangeDeliveryAddressToChosenOne() {
        HomePage basePage = new HomePage();
        CartAddressPage my_address = basePage
                .openHomePage()
                .openLoginPage()
                .login()
                .openHomePage()
                .addProductToCart()
                .proceedToTheCartPage()
                .proceedToAddressPage()
                .chooseDeliveryAddress("My address");
        String chosenDeliveryAddress = my_address.getChosenDeliveryAddress();
        Assert.assertEquals(chosenDeliveryAddress, "My address");
    }

    @Test(description = "FIll in address form with incorrect data",
            dataProvider = "address_form_data_incorrect",dataProviderClass = TestDataProvider.class)
    @Description("Verify if logged in user is getting error alerts when address form filled in with incorrect data")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNotAllowUserToCreateNewAddressUsingIncorrectData(IncorrectAddress address) {
        HomePage basePage = new HomePage();
        AddNewAddressPage addNewAddressPage = basePage
                .openHomePage()
                .openLoginPage()
                .login()
                .openHomePage()
                .addProductToCart()
                .proceedToTheCartPage()
                .proceedToAddressPage()
                .redirectToAddNewDeliveryAddressPage()
                .fillAddressForm(address);
        Assert.assertTrue(addNewAddressPage.getAlertMessage().getText().contains(address.getAlert()));
    }

    @Test(description = "FIll in address form with correct data",
            dataProvider = "address_form_data_correct",dataProviderClass = TestDataProvider.class)
    @Description("Verify if logged in user is able to create new address using correct data")
    @Feature("Cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAllowUserToCreateNewAddressUsingCorrectData(CorrectAddress address) {
        HomePage basePage = new HomePage();
        MyAddressesPage myAddressesPage = basePage
                .openHomePage()
                .openLoginPage()
                .login()
                .openHomePage()
                .addProductToCart()
                .proceedToTheCartPage()
                .proceedToAddressPage()
                .redirectToAddNewDeliveryAddressPage()
                .fillAddressForm(address);
        Assert.assertTrue(myAddressesPage.isAddressExist(address.getAddressTitle()));
    }
}
