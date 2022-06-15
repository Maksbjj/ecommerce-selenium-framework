package com.ecommerce.qa.pageobjects.popup;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.cartpages.CartSummaryPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddToCartPopup extends HomePage {


    @FindBy(css = "div[class*='layer_cart_product'] h2")
    private WebElement successMessage;

    @FindBy(css = "i[class*='icon-ok']")
    private WebElement successIcon;

    @FindBy(css = "div[class*='layer_cart_cart'] h2")
    private WebElement itemsInTheCartMessage;


    @FindBy(css = "a[title='Proceed to checkout'] span")
    private WebElement proceedToCheckoutButton;


    @FindBy(css = "span[title='Continue shopping']")
    private WebElement continueShoppingButton;


    @FindBy(xpath = "//div /strong[contains(text(),'Total products')]")
    private WebElement totalProductsPrice;

    @FindBy(xpath = "//div /strong[contains(text(),'Total shipping')]")
    private WebElement shippingPrice;

    @FindBy(xpath = "(//strong[contains(@class,'dark')][normalize-space()='Total'])[2]")
    private WebElement totalPriceToPay;

    @FindBy(xpath = "(//div[@class='clearfix'])[1]")
    private WebElement popUpWindow;


    public boolean allTheElementDisplayed() {
        waitForElementToBeDisplayed(popUpWindow);
        if (!successMessage.isDisplayed()) {
            return false;
        }
        if (!successIcon.isDisplayed()) {
            return false;
        }
        if (!itemsInTheCartMessage.isDisplayed()) {
            return false;
        }
        if (!proceedToCheckoutButton.isDisplayed()) {
            return false;
        }
        if (!continueShoppingButton.isDisplayed()) {
            return false;
        }
        if (!totalProductsPrice.isDisplayed()) {
            return false;
        }
        if (!shippingPrice.isDisplayed()) {
            return false;
        }
        return totalPriceToPay.isDisplayed();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public CartSummaryPage proceedToTheCartPage(){
        waitForElementToBeDisplayed(proceedToCheckoutButton);
        clickElement(proceedToCheckoutButton);
        return new CartSummaryPage();
    }
}
