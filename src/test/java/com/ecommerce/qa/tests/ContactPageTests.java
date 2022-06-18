package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.ContactPage;
import com.ecommerce.qa.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ecommerce.qa.utils.FakeDataGenerator.randomMessage;
import static com.ecommerce.qa.utils.FakeDataGenerator.validEmail;
import static com.ecommerce.qa.utils.TestDataProvider.alerts;
import static com.ecommerce.qa.utils.TestDataProvider.envConfig;

public class ContactPageTests extends BaseTest {

    @Test(dataProvider = "contact_form_data", dataProviderClass = TestDataProvider.class)
    public void shouldChangeSubjectDescription(String subject, String subjectDescription) {
        HomePage basePage = new HomePage();
        String description = basePage.openHomePage().openContactUsPage().getSubjectDescription(subject);
        Assert.assertEquals(description, subjectDescription);
    }

    @Test(dataProvider = "contact_form_data_subjects", dataProviderClass = TestDataProvider.class)
    public void shouldSuccessfullySubmitContactUsForm(String subject) {
        HomePage basePage = new HomePage();
        ContactPage contactPage = basePage.openHomePage()
                .openContactUsPage()
                .sendMessage(subject, envConfig.getUploadFilePath(),validEmail(),randomMessage());
        String alertMessage = contactPage.getElementsText(basePage.getAlertMessage());
        Assert.assertEquals(alertMessage, alerts.getContactSuccess());
    }

    @Test(dataProvider = "fail_contact_form_data", dataProviderClass = TestDataProvider.class)
    public void shouldFailToSubmitContactForm(String subject,String email,String message,String alertMessage) {
        HomePage basePage = new HomePage();
        ContactPage contactPage = basePage.openHomePage()
                .openContactUsPage()
                .sendMessage(subject, envConfig.getUploadFilePath(),email,message);
        String alert = getElementsText(contactPage.getAlertMessage());
        waitForElementToBeDisplayed(contactPage.getAlertMessage());
        Assert.assertTrue(alert.contains(alertMessage));
    }
}
