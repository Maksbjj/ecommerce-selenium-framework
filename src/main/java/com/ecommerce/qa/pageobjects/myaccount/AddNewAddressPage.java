package com.ecommerce.qa.pageobjects.myaccount;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.utils.pojo.addressform.AddressFormData;
import com.ecommerce.qa.utils.pojo.addressform.CorrectAddress;
import com.ecommerce.qa.utils.pojo.addressform.IncorrectAddress;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddNewAddressPage extends HomePage {

    public AddNewAddressPage() {
        super();
    }

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "company")
    private WebElement companyNameField;

    @FindBy(id = "address1")
    private WebElement firstAddressField;

    @FindBy(id = "address2")
    private WebElement secondAddressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "id_state")
    private WebElement stateDropdownMenu;

    @FindBy(id = "postcode")
    private WebElement postalCodeField;

    @FindBy(id = "id_country")
    private WebElement countryDropdownMenu;

    @FindBy(id = "phone")
    private WebElement homePhoneNumberField;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneNumberField;

    @FindBy(id = "other")
    private WebElement additionalInfoTextArea;

    @FindBy(id = "alias")
    private WebElement addressTitleField;

    @FindBy(id = "submitAddress")
    private WebElement submitChangesButton;

    @FindBy(css = "a[class='btn btn-defaul button button-small']")
    private WebElement backToAddressesButton;


    public  MyAddressesPage fillAddressForm(CorrectAddress address) {
        firstNameField.clear();
        firstNameField.sendKeys(address.getFirstname());
        lastNameField.clear();
        lastNameField.sendKeys(address.getLastname());
        companyNameField.sendKeys(address.getCompany());
        firstAddressField.sendKeys(address.getAddress1());
        secondAddressField.sendKeys(address.getAddress2());
        cityField.sendKeys(address.getCity());
        Select state = new Select(stateDropdownMenu);
        state.selectByVisibleText(address.getState());
        postalCodeField.sendKeys(address.getPostalCode());
        Select country = new Select(countryDropdownMenu);
        country.selectByVisibleText(address.getCountry());
        homePhoneNumberField.sendKeys(address.getHomePhoneNumber());
        mobilePhoneNumberField.sendKeys(address.getMobilePhoneNumber());
        additionalInfoTextArea.sendKeys(address.getAdditionalInfoMessage());
        addressTitleField.clear();
        addressTitleField.sendKeys(address.getAddressTitle());
        clickElement(submitChangesButton);
        return new MyAddressesPage();
    }

    public AddNewAddressPage fillAddressForm(IncorrectAddress address) {
        firstNameField.clear();
        firstNameField.sendKeys(address.getFirstname());
        lastNameField.clear();
        lastNameField.sendKeys(address.getLastname());
        companyNameField.sendKeys(address.getCompany());
        firstAddressField.sendKeys(address.getAddress1());
        secondAddressField.sendKeys(address.getAddress2());
        cityField.sendKeys(address.getCity());
        Select state = new Select(stateDropdownMenu);
        state.selectByVisibleText(address.getState());
        postalCodeField.sendKeys(address.getPostalCode());
        Select country = new Select(countryDropdownMenu);
        country.selectByVisibleText(address.getCountry());
        homePhoneNumberField.sendKeys(address.getHomePhoneNumber());
        mobilePhoneNumberField.sendKeys(address.getMobilePhoneNumber());
        additionalInfoTextArea.sendKeys(address.getAdditionalInfoMessage());
        addressTitleField.clear();
        addressTitleField.sendKeys(address.getAddressTitle());
        clickElement(submitChangesButton);
        return new AddNewAddressPage();
    }
}
