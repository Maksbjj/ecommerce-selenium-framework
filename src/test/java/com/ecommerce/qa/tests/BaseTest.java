package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BrowserType;
import com.ecommerce.qa.base.BasePage;
import org.testng.annotations.*;

import static com.ecommerce.qa.base.DriverContext.*;

public abstract class BaseTest extends BasePage {


    @BeforeTest
    public void readTestData() {
        loadTestData();
    }

    @Parameters({"Browser"})
    @BeforeMethod
    public void setUp(@Optional String browser) {
        initializeBrowser("MicrosoftEdge");
        deleteCookies();
        maximizeWindow();
        setImplicitTimeout();
        setFluentWaits();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
}
