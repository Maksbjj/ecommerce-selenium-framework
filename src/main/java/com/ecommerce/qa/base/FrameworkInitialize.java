package com.ecommerce.qa.base;

import com.ecommerce.qa.config.EnvironmentConfig;
import com.opencsv.CSVReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ecommerce.qa.base.DriverContext.getDriver;

public class FrameworkInitialize {

    public static WebDriverWait wait;
    public static EnvironmentConfig envConfig;
    private Map<String,String> pageTitles;

    public static void initializeBrowser(BrowserType browser) {
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

    public static void setImplicitTimeout() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void setExplicitTimeout() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public void createEnvConfig(){
        envConfig = ConfigFactory.create(EnvironmentConfig.class);
    }

    public void readPageTitles()  {
        pageTitles = new HashMap<>();
        List<String[]> strings;
        try (CSVReader reader = new CSVReader(
                new FileReader("src/main/java/com/ecommerce/qa/files/PageTItles.csv"))) {

            strings = reader.readAll();
        for (String[] string : strings) {
            pageTitles.put(string[0],string[1]);
        }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.log(Level.INFO,"Impossible to read the file");
        }
        }

        public String getPageTitle(String pageName) {
            List<String> strings = pageTitles
                    .entrySet()
                    .stream()
                    .filter(x -> x.getKey().equals(pageName))
                    .map(Map.Entry::getValue).toList();
            return strings.get(0);
        }

}
