package com.ecommerce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.ecommerce.qa.util.FakeDataGenerator.randomMessage;
import static com.ecommerce.qa.util.FakeDataGenerator.randomNumber;


public class ContactPage extends BasePage {

    public ContactPage() {
        super();
    }

    @FindBy(css = "[class*='page-heading bottom-indent']")
    private WebElement pageHeading;

    @FindBy(id = "id_contact")
    private WebElement subjectHeadingDropDown;

    @FindBy(id = "email")
    private WebElement emailAddressField;

    @FindBy(id = "id_order")
    private WebElement orderReferenceInput;

    @FindBy(id = "fileUpload")
    private WebElement fileUploadInput;

    @FindBy(css = "span[class*='filename']")
    private WebElement fileNameField;

    @FindBy(id = "submitMessage")
    private WebElement submitContactFormButton;

    @FindBy(css = "p[id*='desc_']")
    private List<WebElement> subjectDescription;

    @FindBy(id = "message")
    private WebElement textMessageInput;


    public void chooseSubject(String subject) {
        Select select = new Select(subjectHeadingDropDown);
        select.selectByVisibleText(subject);
    }

    public String getSubjectDescription(String subject) {
        String description = null;
        chooseSubject(subject);
        for (WebElement webElement : subjectDescription) {
            if (webElement.isDisplayed())
                description = webElement.getText();
        }
        return description;
    }

    public ContactPage sendMessage(String subject, String filePath) {
        chooseSubject(subject);
        emailAddressField.sendKeys(envConfig.getUserEmail());
        orderReferenceInput.sendKeys(randomNumber());
        fileUploadInput.sendKeys(filePath);
        textMessageInput.sendKeys(randomMessage());
        submitContactFormButton.click();
        return new ContactPage();
    }


}
