package com.ecommerce.qa.utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


import static com.ecommerce.qa.base.DriverContext.*;

public class AllureListener implements ITestListener, ISuiteListener {


    @Override
    public void onStart(ISuite suite) {
        try {
            FileUtils.deleteDirectory(new File("allure-results"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
            saveFailureScreenShoot();
        saveLogText(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveFailureScreenShoot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value ="0",type = "text/plain")
    public static String saveLogText(String message) {
        return  message;
    }
}
