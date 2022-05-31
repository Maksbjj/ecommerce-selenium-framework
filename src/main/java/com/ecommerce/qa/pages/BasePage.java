package com.ecommerce.qa.pages;

import com.ecommerce.qa.base.FrameworkInitialize;
import com.ecommerce.qa.popup.AddToCartPopup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.ecommerce.qa.base.DriverContext.driver;
import static com.ecommerce.qa.base.DriverContext.getDriver;



public class BasePage extends FrameworkInitialize {


    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }


    @FindBy(id = "search_query_top")
    private WebElement searchInput;

    @FindBy(name = "submit_search")
    private WebElement submitSearch;

    @FindBy(css = "ul[id='blockbestsellers'] li")
    private List<WebElement> listOfBestSellers;

    @FindBy(css = "ul[id='homefeatured'] li")
    private List<WebElement> listOfPopularProducts;

    @FindBy(id = "htmlcontent_home")
    private WebElement picturesBlock;

    @FindBy(id = "newsletter-input")
    private WebElement newsletterInput;

    @FindBy(name = "submitNewsletter")
    private WebElement submitNewsletterButton;

    @FindBy(css = "#footer a")
    private List<WebElement> footerLinks;

    @FindBy(xpath = "//button[text()='Shop now !']")
    private WebElement shopNowButton;

    @FindBy(css = "a[class='quick-view-mobile']")
    private WebElement openProductPreviewMobileButton;

    @FindBy(css = "a[class='quick-view']")
    private WebElement openProductPreviewButton;

    @FindBy(css ="div[class*='fancybox-wrap']")
    private WebElement productReviewPopUp;

    @FindBy(css = "span[class='shop-phone']")
    private WebElement callUsNowElement;

    @FindBy(css = "a[class='login']")
    private WebElement loginPageButton;

    @FindBy(css = "a[title='View']")
    private WebElement openProductPageButton;

    @FindBy(xpath = "(//a[@title='Add to cart'])[1]")
    private WebElement addToCartButton;

    @FindBy(css = "li[class*='htmlcontent-item'] img")
    private List<WebElement> listOfHomeContentImages;

    @FindBy(css = "li[class='homeslider-container'] a")
    private List<WebElement> listOfSliderImages;

    @FindBy(css = "img[class*='logo']")
    private WebElement pageLogo;

    @FindBy(css = "a[class*='bx-next']")
    private WebElement sliderNextButton;

    @FindBy(css = "p[class*='alert']")
    private WebElement newsletterAlert;

    @FindBy(xpath = "//a[text()='support@seleniumframework.com']")
    private WebElement redirectToMailToPage;

    @FindBy(xpath = "//a[text()='Selenium Framework']")
    private WebElement redirectToSeleniumPage;

    @FindBy(id = "contact-link")
    private WebElement redirectToContactUsPage;

    @FindBy(xpath = "(//div[@class='product-container'])[1]")
    private WebElement productContainer;



    public BasePage openHomePage() {
        getDriver().get(envConfig.getBaseUrl());
        return new BasePage();
    }


    public LoginPage openLoginPage() {
        loginPageButton.click();
        return new LoginPage();
    }

    public ProductPage openProductPage() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(productContainer).moveToElement(openProductPageButton).click().build().perform();
        return new ProductPage();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean validateHomeContentImages() {
        List<WebElement> webElements = listOfHomeContentImages.stream().filter(WebElement::isDisplayed).toList();
        return listOfHomeContentImages.size() == webElements.size();
    }

    public void sendMailToSupport() {
        redirectToMailToPage.click();
    }

    public void goToSeleniumFrameworkPage() {
        redirectToSeleniumPage.click();
    }

    public ContactPage goToContactUsPage() {
        redirectToContactUsPage.click();
        return new ContactPage();
    }

    public String signUpForNewsletter(String email) {
        newsletterInput.sendKeys(email);
        submitNewsletterButton.click();
        wait.until(ExpectedConditions.visibilityOf(newsletterAlert));
        return newsletterAlert.getText();
    }

    public boolean isLogoPresent() {
        return pageLogo.isDisplayed();
    }

    public AddToCartPopup addProductToCart() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(productContainer).moveToElement(addToCartButton).click().build().perform();
        return new AddToCartPopup();
    }

    public boolean openProductReviewPopUp(){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(productContainer).moveToElement(openProductPreviewButton).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(productReviewPopUp));
        return productReviewPopUp.isDisplayed();
    }
}
