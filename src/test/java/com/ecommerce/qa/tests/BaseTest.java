package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BrowserType;
import com.ecommerce.qa.base.FrameworkInitialize;
import org.testng.annotations.*;

import static com.ecommerce.qa.base.DriverContext.getDriver;

public abstract class BaseTest extends FrameworkInitialize {

    @BeforeMethod
    public void setUp() {
        initializeBrowser(BrowserType.CHROME);
        loadTestData();
        deleteCookies();
        maximizeWindow();
        setImplicitTimeout();
        setExplicitTimeout();
    }

    @AfterTest
    public void cleanUp() {
        getDriver().quit();
    }
}
