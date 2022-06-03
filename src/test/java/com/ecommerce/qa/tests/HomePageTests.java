package com.ecommerce.qa.tests;

import com.ecommerce.qa.pages.BasePage;
import com.ecommerce.qa.pages.ContactPage;
import com.ecommerce.qa.pages.popup.AddToCartPopup;
import com.ecommerce.qa.util.ExcelUtil;
import com.ecommerce.qa.util.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.qa.base.DriverContext.getDriver;
import static com.ecommerce.qa.util.CsvUtil.getPageTitle;

public class HomePageTests extends BaseTest {

    @Test
    public void shouldReturnActualPageTitle() {
        BasePage homePage = new BasePage();

        String pageTitle = homePage.openHomePage().getPageTitle();
        Assert.assertEquals(pageTitle, getPageTitle("Home"));
    }

    @Test(dataProvider = "newsletter_data", dataProviderClass = TestDataProvider.class)
    public void shouldReturnCorrectNewsletterAlertMessage(String email, String alertMessage) {
        BasePage homePage = new BasePage();
        String message = homePage.openHomePage().signUpForNewsletter(email);
        Assert.assertEquals(message, alertMessage);
    }


    @Test
    public void shouldDisplayHomeContentImages() {
        BasePage homePage = new BasePage();

        boolean areImagesDisplayed = homePage.openHomePage().validateHomeContentImages();
        Assert.assertTrue(areImagesDisplayed);
    }

    @Test
    public void shouldDisplayHomePageLogo() {
        BasePage homePage = new BasePage();
        boolean isLogoDisplayed = homePage.openHomePage().isLogoPresent();
        Assert.assertTrue(isLogoDisplayed);
    }

    @Test
    public void shouldRedirectToProductPage() {
        BasePage homePage = new BasePage();
        homePage.openHomePage().openProductPage();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("product"));
    }

    @Test
    public void shouldAddProductToCart() {
        ExcelUtil alerts = new ExcelUtil(envConfig.getExcelAlertsPath());
        BasePage homePage = new BasePage();
        AddToCartPopup addToCartPopup = homePage.openHomePage().addProductToCart();
        boolean allElementsDisplayed = addToCartPopup.allTheElementDisplayed();
        String successMessage = addToCartPopup.getSuccessMessage();
        Assert.assertTrue(allElementsDisplayed);
        Assert.assertEquals(successMessage, alerts.getCellValue(0, 3));
    }

    @Test
    public void shouldRedirectToContactUsPage() {
        BasePage homePage = new BasePage();
        ContactPage contactPage = homePage.openHomePage().goToContactUsPage();
        Assert.assertEquals(contactPage.getPageTitle(), getPageTitle("Contact Us"));
    }

    @Test
    public void shouldDisplayProductReviewPopUpWindow() {
        BasePage homePage = new BasePage();
        boolean isPopUpDisplayed = homePage.openHomePage().openProductReviewPopUp();
        Assert.assertTrue(isPopUpDisplayed);
    }
}
