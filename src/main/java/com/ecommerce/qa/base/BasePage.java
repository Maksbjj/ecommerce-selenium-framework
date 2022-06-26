package com.ecommerce.qa.base;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.ecommerce.qa.utils.pojo.Alerts;
import com.ecommerce.qa.utils.pojo.PageTitle;
import com.ecommerce.qa.utils.pojo.addressform.AddressFormData;
import com.ecommerce.qa.utils.pojo.contactpage.ContactSubject;
import com.ecommerce.qa.utils.pojo.homepagecategories.Root;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static com.ecommerce.qa.base.DriverContext.driver;
import static com.ecommerce.qa.utils.JsonUtil.readJsonToPojo;
import static com.ecommerce.qa.utils.TestDataProvider.*;

public class BasePage {

    public static FluentWait<WebDriver> fluentWaitLong;
    public static FluentWait<WebDriver> fluentWaitShort;

    private static final int WAIT_TIMEOUT_LONG = 60;
    private static final int WAIT_TIMEOUT_SHORT = 30;
    private static final int WAIT_POLLING_TIMEOUT = 500;
    private static final String GRID_LOCAL_URL = "http://192.168.8.104:4444";


    public static void initializeBrowser(String browserName) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.ANY);
        try {
            switch (browserName) {
                case "chrome" -> {
                    cap.setBrowserName(browserName);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.merge(cap);
                    driver = new RemoteWebDriver(new URL(GRID_LOCAL_URL), chromeOptions);
                }
//                case "firefox" -> {
//                    cap.setBrowserName(browserName);
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//                    chromeOptions.merge(cap);
//                    driver = new RemoteWebDriver(new URL(GRID_LOCAL_URL), chromeOptions);
//                }
                case "MicrosoftEdge" -> {
                    cap.setBrowserName(browserName);
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.merge(cap);
                    driver = new RemoteWebDriver(new URL(GRID_LOCAL_URL), edgeOptions);
                }
            }
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public void setImplicitTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void setFluentWaits() {
        fluentWaitLong = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(WAIT_POLLING_TIMEOUT))
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_LONG))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        fluentWaitShort = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(WAIT_POLLING_TIMEOUT))
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SHORT))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public static void loadTestData() {
        envConfig = ConfigFactory.create(EnvironmentConfig.class);
        alerts = readJsonToPojo(Alerts.class, envConfig.getAlertsPath());
        pageTitles = readJsonToPojo(PageTitle.class, envConfig.getPageTitlesPath());
        subjects = readJsonToPojo(ContactSubject.class, envConfig.getContactSubjectsPath());
        root = readJsonToPojo(Root.class, envConfig.getHomePageCategoriesPath());
        addressFormData = readJsonToPojo(AddressFormData.class, envConfig.getAddressFormDataPath());
    }

    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isElementPresented(WebElement element) {
        return element.isDisplayed();
    }


    public String getElementsText(WebElement element) {
        return element.getText();
    }

    public void waitForElementToBeClickable(WebElement element) {
        fluentWaitShort.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeDisplayed(WebElement element) {
        fluentWaitShort.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementsToBeDisplayed(List<WebElement> list) {
        fluentWaitShort.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    protected void waitUntilJqueryIsDone() {
        fluentWaitLong.until((ExpectedCondition<Boolean>) x -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (Boolean) js.executeScript("return jQuery.active == 0");
        });
    }
}


