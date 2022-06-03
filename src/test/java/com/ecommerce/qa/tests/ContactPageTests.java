package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ContactPage;
import com.ecommerce.qa.util.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

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
                .sendMessage(subject, envConfig.getUploadFilePath());
        String alertMessage = contactPage.getAlertMessage();
        Assert.assertEquals(alertMessage, alerts.getCellValue(0, 10));
    }

    @Test
    public void shouldFailToSentMessageWithoutChosenSubject() {
        BasePage basePage = new BasePage();
        ContactPage contactPage = basePage.openHomePage()
                .goToContactUsPage()
                .sendMessage("-- Choose --", envConfig.getUploadFilePath());
        String alertMessage = contactPage.getAlertMessage();
        Assert.assertTrue(alertMessage.contains(alerts.getCellValue(0, 11)));
    }

}
