package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ContactPage;
import com.ecommerce.qa.util.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ecommerce.qa.util.FakeDataGenerator.randomMessage;
import static com.ecommerce.qa.util.FakeDataGenerator.validEmail;

public class ContactPageTests extends BaseTest {

    @Test(dataProvider = "contact_form_data", dataProviderClass = TestDataProvider.class)
    public void shouldChangeSubjectDescription(String subject, String subjectDescription) {
        BasePage basePage = new BasePage();
        String description = basePage.openHomePage().goToContactUsPage().getSubjectDescription(subject);
        Assert.assertEquals(description, subjectDescription);
    }

    @Test(dataProvider = "contact_form_data_subjects", dataProviderClass = TestDataProvider.class)
    public void shouldSuccessfullySubmitContactUsForm(String subject) {
        BasePage basePage = new BasePage();
        ContactPage contactPage = basePage.openHomePage()
                .goToContactUsPage()
                .sendMessage(subject, envConfig.getUploadFilePath(),validEmail(),randomMessage());
        String alertMessage = contactPage.getAlertMessage();
        Assert.assertEquals(alertMessage, alerts.getContactSuccess());
    }

    @Test(dataProvider = "fail_contact_form_data", dataProviderClass = TestDataProvider.class)
    public void shouldFailToSubmitContactForm(String subject,String email,String message,String alertMessage) {
        BasePage basePage = new BasePage();
        ContactPage contactPage = basePage.openHomePage()
                .goToContactUsPage()
                .sendMessage(subject, envConfig.getUploadFilePath(),email,message);
        String alert = contactPage.getAlertMessage();
        Assert.assertTrue(alert.contains(alertMessage));
    }
}
