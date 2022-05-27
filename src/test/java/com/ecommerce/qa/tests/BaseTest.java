package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BrowserType;
import com.ecommerce.qa.base.FrameworkInitialize;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.ecommerce.qa.base.DriverContext.getDriver;

public class BaseTest extends FrameworkInitialize {

    @BeforeMethod
    public void setUp() {
        initializeBrowser(BrowserType.Chrome);
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
