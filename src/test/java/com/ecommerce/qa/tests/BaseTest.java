package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BrowserType;
import com.ecommerce.qa.base.FrameworkInitialize;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.ecommerce.qa.base.DriverContext.getDriver;

public abstract class BaseTest extends FrameworkInitialize {

    @BeforeMethod
    public void setUp() {
        initializeBrowser(BrowserType.CHROME);
        deleteCookies();
        maximizeWindow();
        createEnvConfig();
        setImplicitTimeout();
        setExplicitTimeout();
        createEnvConfig();
        loadTestData();
    }

    @AfterMethod
    public void cleanUp() {
        getDriver().quit();
    }
}
