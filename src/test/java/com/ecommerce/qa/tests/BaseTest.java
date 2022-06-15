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

    @BeforeMethod
    public void setUp() {
        initializeBrowser(BrowserType.CHROME);
        deleteCookies();
        maximizeWindow();
        setImplicitTimeout();
        setFluentWaits();
    }

    @AfterMethod
    public void cleanUp() {
        getDriver().quit();
    }
}
