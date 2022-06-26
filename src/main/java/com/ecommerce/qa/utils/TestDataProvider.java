package com.ecommerce.qa.utils;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.utils.pojo.Alerts;
import com.ecommerce.qa.utils.pojo.PageTitle;
import com.ecommerce.qa.utils.pojo.addressform.AddressFormData;
import com.ecommerce.qa.utils.pojo.addressform.CorrectAddress;
import com.ecommerce.qa.utils.pojo.addressform.IncorrectAddress;
import com.ecommerce.qa.utils.pojo.contactpage.ContactSubject;
import com.ecommerce.qa.utils.pojo.homepagecategories.Root;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.ecommerce.qa.utils.FakeDataGenerator.*;

public class TestDataProvider {

    public static EnvironmentConfig envConfig;
    public static PageTitle pageTitles;
    public static Root root;
    public static ContactSubject subjects;
    public static Alerts alerts;
    public static AddressFormData addressFormData;


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
                "", randomMessage(), alerts.getInvalidEmail()},
                {subjects.getSubjects()[1].getName(),
                        randomNumber(), randomMessage(), alerts.getInvalidEmail()},
                {subjects.getSubjects()[0].getName(),
                        validEmail(), "", alerts.getBlankMessageError()}};
    }

    @DataProvider(name = "homepage_product_categories")
    public Object[][] getHomePageProductCategories() {
        return new Object[][]{{HomePage.WOMEN_CATEGORY_NAME}, {HomePage.DRESSES_CATEGORY_NAME}};
    }

    @DataProvider(name = "address_form_data_correct")
    public Iterator<Object[]> getCorrectAddressFormData() {
        List<Object[]> args = new ArrayList<>();
        for (CorrectAddress address : addressFormData.getCorrect()) {
            args.add(new Object[]{address});
        }
        return args.iterator();
    }

    @DataProvider(name = "address_form_data_incorrect")
    public Iterator<Object[]> getIncorrectAddressFormData(){
        List<Object[]> args = new ArrayList<>();
        for (IncorrectAddress address : addressFormData.getIncorrect()) {
            args.add(new Object[]{address});
        }
        return args.iterator();
    }

}
