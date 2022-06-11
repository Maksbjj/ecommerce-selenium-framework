package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BrowserType;
import com.ecommerce.qa.base.FrameworkInitialize;
import org.testng.annotations.*;

import static com.ecommerce.qa.base.DriverContext.getDriver;
import static com.ecommerce.qa.base.DriverContext.initializeDriver;

public abstract class BaseTest extends FrameworkInitialize {


    @BeforeTest
    public void readTestData() {
        loadTestData();
    }

    @BeforeMethod
    public void setUp() {
        initializeDriver();
        initializeBrowser(BrowserType.CHROME);
        deleteCookies();
        maximizeWindow();
        setImplicitTimeout();
        setExplicitTimeout();
    }

    @AfterMethod
    public void cleanUp() {
        getDriver().quit();
    }
}
