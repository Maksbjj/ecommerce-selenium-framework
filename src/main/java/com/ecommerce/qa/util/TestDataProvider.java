package com.ecommerce.qa.util;

import com.ecommerce.qa.config.EnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.DataProvider;

public class TestDataProvider extends TestDataGenerator {

    @DataProvider(name = "newsletter_data")
    public Object[][] getNewsletterInputData() {
        EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
        return new Object[][]{{validEmail(), envConfig.newsletterAlertMessage()}
                , {invalidEmail(), envConfig.newsletterInvalidEmailMessage()}
                , {envConfig.registeredEmail(), envConfig.newsletterFailedMessage()}};
    }
}
