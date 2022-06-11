package com.ecommerce.qa.util;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.ecommerce.qa.util.pojo.Alerts;
import com.ecommerce.qa.util.pojo.ContactSubject;
import com.ecommerce.qa.util.pojo.PageTitle;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import static com.ecommerce.qa.util.FakeDataGenerator.*;

public class TestDataProvider {

    protected static JSONObject jsonData;
    public static EnvironmentConfig envConfig;
    protected static PageTitle pageTitles;
    protected static ContactSubject subjects;
    protected static Alerts alerts;


    @DataProvider(name = "newsletter_data")
    public Object[][] getNewsletterInputData() {
        return new Object[][]{{validEmail(), alerts.getNewsletterSuccess()}
                , {invalidEmail(), alerts.getNewsletterInvalidEmail()}
                , {envConfig.getUserEmail(), alerts.getNewsletterRegistered()}};
    }

    @DataProvider(name = "forgot_password_data")
    public Object[][] getForgotPasswordData() {
        return new Object[][]{{validEmail(), alerts.getNoAccount()}
                , {invalidEmail(), alerts.getInvalidEmail()}
                , {envConfig.getUserEmail(), alerts.getConfirmationSuccess()}};
    }

    @DataProvider(name = "failed_login_data")
    public static Object[][] getFailedLoginCredentials() {
        return new Object[][]{{"", randomPassword(), alerts.getEmailRequired(),}
                , {validEmail(), randomPassword(), alerts.getAuthFailed()}
                , {validEmail(), "", alerts.getPasswordRequired()}};
    }

    @DataProvider(name = "contact_form_data")
    public Object[][] getContactUsFormData() {
        return new Object[][]{{subjects.getSubjects()[0].getName(),
                subjects.getSubjects()[0].getDescription()},
                {subjects.getSubjects()[1].getName(),
                        subjects.getSubjects()[1].getDescription()}};
    }

    @DataProvider(name = "contact_form_data_subjects")
    public Object[][] getContactFormSubjects() {
        return new Object[][]{{subjects.getSubjects()[0].getName()},
                {subjects.getSubjects()[1].getName()}};
    }

    @DataProvider(name = "fail_contact_form_data")
    public Object[][] getFailContactFormData() {
        return new Object[][]{{subjects.getSubjects()[0].getName(),
                "", randomMessage(), alerts.getEmailRequired()},
                {subjects.getSubjects()[1].getName(),
                        randomNumber(), randomMessage(), alerts.getInvalidEmail()},
                {subjects.getSubjects()[0].getName(),
                        validEmail(),"",alerts.getBlankMessageError()}};
    }
}
