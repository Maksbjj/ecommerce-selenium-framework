package com.ecommerce.qa.base;

import org.openqa.selenium.WebDriver;

public class DriverContext {

    public static ThreadLocal<WebDriver> driver;

    public static void initializeDriver() {
        driver = new ThreadLocal<>();
    }

    public static WebDriver getDriver() {
        return DriverContext.driver.get();
    }
}
