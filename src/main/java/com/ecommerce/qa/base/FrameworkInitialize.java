package com.ecommerce.qa.base;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.ecommerce.qa.util.TestDataProvider;
import com.ecommerce.qa.util.pojo.Alerts;
import com.ecommerce.qa.util.pojo.ContactSubject;
import com.ecommerce.qa.util.pojo.PageTitle;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static com.ecommerce.qa.base.DriverContext.getDriver;
import static com.ecommerce.qa.util.JsonUtil.readJsonToPojo;

public class FrameworkInitialize extends TestDataProvider {

    public static WebDriverWait wait;

    public void initializeBrowser(BrowserType browser) {
        switch (browser) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                DriverContext.driver.set(new FirefoxDriver());
            }
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                DriverContext.driver.set(new ChromeDriver());
            }
            case IE -> {
                WebDriverManager.iedriver().setup();
                DriverContext.driver.set(new InternetExplorerDriver());
            }
            case SAFARI -> {
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

    public void setImplicitTimeout() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void setExplicitTimeout() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public static void loadTestData() {
        envConfig = ConfigFactory.create(EnvironmentConfig.class);
        alerts = readJsonToPojo(Alerts.class,envConfig.getAlertsPath());
        pageTitles = readJsonToPojo(PageTitle.class,envConfig.getPageTitlesPath());
        subjects = readJsonToPojo(ContactSubject.class,envConfig.getContactSubjectsPath());
    }
}
