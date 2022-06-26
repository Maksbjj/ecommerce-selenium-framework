package com.ecommerce.qa.tests;

import com.ecommerce.qa.base.BasePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static com.ecommerce.qa.base.DriverContext.driver;

public abstract class BaseTest extends BasePage {


    @BeforeTest
    public void readTestData() {
        loadTestData();
    }

//    @Parameters({"Browser"})
//    @BeforeMethod
//    public void setUp(String browser) {
//        initializeBrowser(browser);
//        deleteCookies();
//        maximizeWindow();
//        setImplicitTimeout();
//        setFluentWaits();
//    }

    @Parameters({"Browser"})
    @BeforeMethod
    public void setUp() {
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
