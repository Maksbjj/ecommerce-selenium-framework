package com.ecommerce.qa.base;

import org.openqa.selenium.WebDriver;

public class DriverContext {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }
}
