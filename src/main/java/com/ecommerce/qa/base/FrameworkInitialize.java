package com.ecommerce.qa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.ecommerce.qa.base.DriverContext.getDriver;

public class FrameworkInitialize {

    public static WebDriverWait wait;

    public static void initializeBrowser(BrowserType browser) {
        switch (browser) {
            case Firefox -> {
                WebDriverManager.firefoxdriver().setup();
                DriverContext.driver.set(new FirefoxDriver());
            }
            case Chrome -> {
                WebDriverManager.chromedriver().setup();
                DriverContext.driver.set(new ChromeDriver());
            }
            case IE -> {
                WebDriverManager.iedriver().setup();
                DriverContext.driver.set(new InternetExplorerDriver());
            }
            case Safari -> {
                WebDriverManager.safaridriver().setup();
                DriverContext.driver.set(new SafariDriver());
            }
        }
    }

    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public static void setImplicitTimeout() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void setExplicitTimeout() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

}
