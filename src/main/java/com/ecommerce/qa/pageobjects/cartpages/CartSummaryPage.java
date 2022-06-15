package com.ecommerce.qa.pageobjects.cartpages;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartSummaryPage extends HomePage {

    public CartSummaryPage() {
        super();
    }

    @FindBy(css = "[id*='cart_quantity_down']")
    private List<WebElement> cartQuantityDownButtons;

    @FindBy(css = "[id*='cart_quantity_up']")
    private List<WebElement> cartQuantityUpButtons;

    @FindBy(css ="[class='cart_quantity text-center'] input[type='hidden']")
    public List<WebElement> quantityInputs;

    @FindBy(css = "a[title='Delete']")
    private List<WebElement> deleteFromCartButtons;

    @FindBy(css = "td[class*='cart_product'] img")
    private List<WebElement> productImages;

    @FindBy(css ="span[class*='label label-success']")
    private List<WebElement> availabilityLabels;

    @FindBy(css ="a[title*='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(css = "a[class*='standard-checkout button'] span")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = "[class*='address first_item']")
    private WebElement deliveryAddress;

    @FindBy(css = "[class*='address last_item']")
    private WebElement invoiceAddress;


    public boolean areAddressesDisplayed(){
        return deliveryAddress.isDisplayed() || invoiceAddress.isDisplayed();
    }


    public CartSummaryPage deleteProductFromCart(){
        for (WebElement deleteFromCartButton : deleteFromCartButtons) {
            clickElement(deleteFromCartButton);
        }
        return new CartSummaryPage();
    }

    public LoginPage proceedToLoginPage(){
        clickElement(proceedToCheckoutButton);
        return new LoginPage();
    }

    public CartAddressPage proceedToAddressPage(){
        clickElement(proceedToCheckoutButton);
        return new CartAddressPage();
    }



    public String getProductQuantity(){

        return quantityInputs.get(0).getAttribute("value");
    }

    public void increaseProductQuantity(){
        clickElement(cartQuantityUpButtons.get(0));
    }

    public void decreaseProductQuantity(){
        clickElement(cartQuantityDownButtons.get(0));
    }

    public ExpectedCondition<Boolean> isQuantityIncreasedByOne(int beforeValue){
        return ExpectedConditions.domAttributeToBe(quantityInputs.get(0),
                "value", String.valueOf(beforeValue + 1));
    }

    public ExpectedCondition<Boolean> isQuantityDecreasedByOne(int beforeValue){
        return ExpectedConditions.domAttributeToBe(quantityInputs.get(0),
                "value", String.valueOf(beforeValue - 1));
    }

}
