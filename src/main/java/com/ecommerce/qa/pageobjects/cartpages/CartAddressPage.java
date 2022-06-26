package com.ecommerce.qa.pageobjects.cartpages;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.myaccount.AddNewAddressPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CartAddressPage extends HomePage {

    public CartAddressPage() {
        super();
    }


    @FindBy(id = "id_address_delivery")
    private WebElement choseDeliveryAddressDropdown;

    @FindBy(id = "addressesAreEquals")
    private WebElement addressesAreEqualCheckbox;

    @FindBy(css = "ul[id='address_invoice'] a")
    private WebElement updateBillingAddressButton;

    @FindBy(css = "ul[id='address_delivery'] a")
    private WebElement updateDeliveryAddressButton;

    @FindBy(css = "p[class*='address_add'] a")
    private WebElement addNewDeliveryAddressButton;

    @FindBy(css = "div[id='address_invoice_form'] a")
    private WebElement addNewBillingAddressButton;

    @FindBy(css = "textarea[name='message']")
    private WebElement textMessageArea;

    @FindBy(css = "div[id='uniform-id_address_delivery'] span")
    private WebElement chosenDeliveryAddress;


    public String getChosenDeliveryAddress() {
        return getElementsText(chosenDeliveryAddress);
    }


    public CartAddressPage chooseDeliveryAddress(String addressName) {
        Select select = new Select(choseDeliveryAddressDropdown);
        select.selectByVisibleText(addressName);
        return new CartAddressPage();
    }

    public AddNewAddressPage redirectToAddNewDeliveryAddressPage() {
        waitForElementToBeClickable(addNewDeliveryAddressButton);
        clickElement(addNewDeliveryAddressButton);
        return new AddNewAddressPage();
    }

    public AddNewAddressPage redirectToAddNewBillingAddressPage() {
        waitForElementToBeClickable(addNewBillingAddressButton);
        clickElement(addNewBillingAddressButton);
        return new AddNewAddressPage();
    }

    public AddNewAddressPage redirectToUpdateBillingAddressPage() {
        waitForElementToBeClickable(updateBillingAddressButton);
        clickElement(updateBillingAddressButton);
        return new AddNewAddressPage();
    }

    public AddNewAddressPage redirectToUpdateDeliveryAddressPage() {
        waitForElementToBeClickable(updateDeliveryAddressButton);
        clickElement(updateDeliveryAddressButton);
        return new AddNewAddressPage();
    }

    public AddNewAddressPage addCommentMessage(String message) {
        waitForElementToBeDisplayed(textMessageArea);
        textMessageArea.sendKeys(message);
        return new AddNewAddressPage();
    }

}
