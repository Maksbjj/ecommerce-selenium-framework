package com.ecommerce.qa.util;

import com.ecommerce.qa.config.EnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.DataProvider;

public class TestDataProvider extends FakeDataGenerator {

    ExcelUtil alerts = new ExcelUtil("src/main/java/com/ecommerce/qa/files/alert.xls");


    @DataProvider(name = "newsletter_data")
    public Object[][] getNewsletterInputData() {
        EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
        return new Object[][]{{validEmail(), alerts.getCellValue(0,0)}
                , {invalidEmail(), alerts.getCellValue(0,2)}
                , {envConfig.getUserPassword(), alerts.getCellValue(0,1)}};
    }

    @DataProvider(name = "forgot_password_data")
    public Object[][] getForgotPasswordData() {
        EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
        return new Object[][]{{validEmail(), alerts.getCellValue(0,5)}
                , {invalidEmail(), alerts.getCellValue(0,4)}
                , {envConfig.getUserEmail(), alerts.getCellValue(0,6)}};
    }

    @DataProvider(name = "failed_login_data")
    public Object[][] getFailedLoginCredentials() {
        EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
        return new Object[][]{{"",randomPassword(), alerts.getCellValue(0,7),}
                , {validEmail(),randomPassword(), alerts.getCellValue(0,8)}
                , {validEmail(),"", alerts.getCellValue(0,9)}};
    }
}
