package com.ecommerce.qa.tests;

import com.ecommerce.qa.pageobjects.HomePage;
import com.ecommerce.qa.pageobjects.ContactPage;
import com.ecommerce.qa.pageobjects.ProductPage;
import com.ecommerce.qa.pageobjects.popup.AddToCartPopup;
import com.ecommerce.qa.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ecommerce.qa.utils.TestDataProvider.alerts;
import static com.ecommerce.qa.utils.TestDataProvider.pageTitles;


public class HomePageTests extends BaseTest {


    @Test(dataProvider = "newsletter_data", dataProviderClass = TestDataProvider.class)
    public void shouldReturnCorrectNewsletterAlertMessage(String email, String alertMessage) {
        HomePage homePage = new HomePage();
        String message = homePage.openHomePage().signUpForNewsletter(email);
        Assert.assertEquals(message, alertMessage);
    }


    @Test
    public void shouldDisplayHomeContentImages() {
        HomePage homePage = new HomePage();
        boolean areImagesDisplayed = homePage.openHomePage().validateHomeContentImages();
        Assert.assertTrue(areImagesDisplayed);
    }

    @Test
    public void shouldDisplayHomePageLogo() {
        HomePage homePage = new HomePage();
        boolean isLogoDisplayed = homePage.openHomePage().isLogoPresented();
        Assert.assertTrue(isLogoDisplayed);
    }

    @Test
    public void shouldRedirectToProductPage() {
        HomePage homePage = new HomePage();
        ProductPage productPage = homePage.openHomePage().openProductPage();
        Assert.assertTrue(getPageTitle().contains(productPage.getProductName()));
    }

    @Test
    public void shouldAddProductToCartFromHomePage() {
        HomePage homePage = new HomePage();
        AddToCartPopup addToCartPopup = homePage.openHomePage().addProductToCart();
        boolean allElementsDisplayed = addToCartPopup.allTheElementDisplayed();
        String successMessage = addToCartPopup.getSuccessMessage();
        Assert.assertTrue(allElementsDisplayed);
        Assert.assertEquals(successMessage, alerts.getProductSuccess());
    }


    @Test
    public void shouldRedirectToContactUsPage() {
        HomePage homePage = new HomePage();
        ContactPage contactPage = homePage.openHomePage().openContactUsPage();
        Assert.assertEquals(contactPage.getPageTitle(), pageTitles.getContactUs());
    }

    @Test
    public void shouldDisplayProductReviewPopUpWindow() {
        HomePage homePage = new HomePage();
        boolean isPopUpDisplayed = homePage.openHomePage().openProductReviewPopUp();
        Assert.assertTrue(isPopUpDisplayed);
    }

    @Test(dataProvider = "homepage_product_categories",dataProviderClass = TestDataProvider.class)
    public void shouldDisplayAllTheContentInsideCategoryDropdownMenu(String categoryName){
        HomePage homePage = new HomePage();
        boolean areAllTheCategoriesPresent = homePage.openHomePage().areAllTheCategoriesPresent(categoryName);
        Assert.assertTrue(areAllTheCategoriesPresent);
    }

}
