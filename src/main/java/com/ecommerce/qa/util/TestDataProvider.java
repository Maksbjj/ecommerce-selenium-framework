package com.ecommerce.qa.util;

import org.testng.annotations.DataProvider;

import java.util.Map;

import static com.ecommerce.qa.base.FrameworkInitialize.envConfig;
import static com.ecommerce.qa.util.FakeDataGenerator.*;

public class TestDataProvider {

    public static ExcelUtil alerts;
    protected static Map<String, String> pageTitles;

    @DataProvider(name = "newsletter_data")
    public Object[][] getNewsletterInputData() {
        return new Object[][]{{validEmail(), alerts.getCellValue(0, 0)}
                , {invalidEmail(), alerts.getCellValue(0, 2)}
                , {envConfig.getUserPassword(), alerts.getCellValue(0, 1)}};
    }

    @DataProvider(name = "forgot_password_data")
    public Object[][] getForgotPasswordData() {
        return new Object[][]{{validEmail(), alerts.getCellValue(0, 5)}
                , {invalidEmail(), alerts.getCellValue(0, 4)}
                , {envConfig.getUserEmail(), alerts.getCellValue(0, 6)}};
    }

    @DataProvider(name = "failed_login_data")
    public Object[][] getFailedLoginCredentials() {
        return new Object[][]{{"", randomPassword(), alerts.getCellValue(0, 7),}
                , {validEmail(), randomPassword(), alerts.getCellValue(0, 8)}
                , {validEmail(), "", alerts.getCellValue(0, 9)}};
    }

    @DataProvider(name = "contact_form_data")
    public Object[][] getContactUsFormData() {
        return new Object[][]{{"Customer service", "For any question about a product, an order"},
                {"Webmaster", "If a technical problem occurs on this website"}};
    }

    @DataProvider(name = "contact_form_data_subjects")
    public Object[][] getContactFormSubjects() {
        return new Object[][]{{"Customer service"},
                {"Webmaster"}};
    }
}
