package com.ecommerce.qa.base;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.ecommerce.qa.utils.pojo.Alerts;
import com.ecommerce.qa.utils.pojo.contactpage.ContactSubject;
import com.ecommerce.qa.utils.pojo.PageTitle;
import com.ecommerce.qa.utils.pojo.homepagecategories.Root;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

import static com.ecommerce.qa.base.DriverContext.getDriver;
import static com.ecommerce.qa.utils.JsonUtil.readJsonToPojo;
import static com.ecommerce.qa.utils.TestDataProvider.*;

public class BasePage {

    public static FluentWait<WebDriver> fluentWaitLong;
    public static FluentWait<WebDriver> fluentWaitShort;

    private static final int WAIT_TIMEOUT_LONG = 60;
    private static final int WAIT_TIMEOUT_SHORT = 30;
    private static final int WAIT_POLLING_TIMEOUT = 500;

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

    public static void setFluentWaits() {
        fluentWaitLong = new FluentWait<>(getDriver())
                .pollingEvery(Duration.ofMillis(WAIT_POLLING_TIMEOUT))
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_LONG))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        fluentWaitShort = new FluentWait<>(getDriver())
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
        root = readJsonToPojo(Root.class,envConfig.getHomePageCategoriesPath());
    }

    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
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
        fluentWaitLong.until((ExpectedCondition<Boolean>) driver -> {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            return (Boolean) js.executeScript("return jQuery.active == 0");
        });
    }
}


