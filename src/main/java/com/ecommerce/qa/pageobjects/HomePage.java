package com.ecommerce.qa.pageobjects;

import com.ecommerce.qa.base.BasePage;
import com.ecommerce.qa.pageobjects.cartpages.CartSummaryPage;
import com.ecommerce.qa.pageobjects.popup.AddToCartPopup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.ecommerce.qa.base.DriverContext.driver;
import static com.ecommerce.qa.utils.TestDataProvider.*;


public class HomePage extends BasePage {

    public static final String WOMEN_CATEGORY_NAME = "Women";
    public static final String DRESSES_CATEGORY_NAME = "Dresses";


    public HomePage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "search_query_top")
    private WebElement searchInput;

    @FindBy(name = "submit_search")
    private WebElement submitSearch;

    @FindBy(css = "div[class='shopping_cart'] a")
    private WebElement openCartButton;

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

    @FindBy(css = "div[class*='fancybox-wrap']")
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

    @FindBy(css = ".alert")
    private WebElement alertMessage;

    @FindBy(xpath = "//a[text()='support@seleniumframework.com']")
    private WebElement redirectToMailToPage;

    @FindBy(xpath = "//a[text()='Selenium Framework']")
    private WebElement seleniumFrameworkPageLink;

    @FindBy(id = "contact-link")
    private WebElement redirectToContactUsPage;

    @FindBy(xpath = "(//div[@class='product-container'])[1]")
    private WebElement productContainer;

    @FindBy (css = "li a[title='Women']")
    private WebElement womenCategory;

    @FindBy (css = "li a[title='Dresses']")
    private List<WebElement> dressesCategory;

    @FindBy(css = "li[class='sfHover'] li a[class='sf-with-ul']")
    private List<WebElement> womenMenuCategories;

    @FindBy(css = "li[class='sfHover']  li ul a")
    private List<WebElement> womenSubCategories;
    
    @FindBy(css = "li[class='sfHover'] li")
    private List<WebElement> dressCategories;


    public HomePage openHomePage() {
        driver.get(envConfig.getBaseUrl());
        return new HomePage();
    }


    public LoginPage openLoginPage() {
        clickElement(loginPageButton);
        return new LoginPage();
    }

    public ProductPage openProductPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(productContainer).moveToElement(openProductPageButton).click().build().perform();
        return new ProductPage();
    }

    public ContactPage openContactUsPage() {
        clickElement(redirectToContactUsPage);
        return new ContactPage();
    }

    public CartSummaryPage openCartPage(){
        clickElement(openCartButton);
        return new CartSummaryPage();
    }

    public boolean validateHomeContentImages() {
        List<WebElement> webElements = listOfHomeContentImages.stream().filter(WebElement::isDisplayed).toList();
        return listOfHomeContentImages.size() == webElements.size();
    }


    public String signUpForNewsletter(String email) {
        newsletterInput.sendKeys(email);
        clickElement(submitNewsletterButton);
        waitForElementToBeDisplayed(alertMessage);
        return alertMessage.getText();
    }


    public AddToCartPopup addProductToCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(productContainer).moveToElement(addToCartButton).click().build().perform();
        return new AddToCartPopup();
    }

    public boolean openProductReviewPopUp() {
        Actions actions = new Actions(driver);
        actions.moveToElement(productContainer).moveToElement(openProductPreviewButton).click().build().perform();
        waitForElementToBeDisplayed(productReviewPopUp);
        return isElementPresented(productReviewPopUp);
    }

    public WebElement getAlertMessage(){
        return alertMessage;
    }


    public boolean isLogoPresented() {
        return isElementPresented(pageLogo);
    }


    public boolean areAllTheCategoriesPresent(String categoryName) {
        List<String> listOfAllCategories = createListOfAllSubCategories();
        chooseCategory(categoryName);

        if (categoryName.equalsIgnoreCase(WOMEN_CATEGORY_NAME)) {
            waitForElementsToBeDisplayed(womenMenuCategories);
            waitForElementsToBeDisplayed(womenSubCategories);
            for (int i = 0; i < womenMenuCategories.size(); i++) {
                if (!womenMenuCategories.get(i).getText()
                        .equalsIgnoreCase(root.getWomen().getCategories().get(i).getName())) {
                    return false;
                }
            }
            for (WebElement womenSubCategory : womenSubCategories) {
                if(!listOfAllCategories.contains(womenSubCategory.getText())) {
                    return false;
                }
            }
        } else if(categoryName.equalsIgnoreCase(DRESSES_CATEGORY_NAME)) {
            waitForElementsToBeDisplayed(dressCategories);
            for (int i = 0; i < dressCategories.size(); i++) {
               if(!dressCategories.get(i).getText().equalsIgnoreCase(root.getWomen().getDresses().get(i).getName())) {
                   return false;
                }
            }
        }
        return true;
        }

    public void chooseCategory(String categoryName) {
        Actions actions = new Actions(driver);
        if (categoryName.equalsIgnoreCase(DRESSES_CATEGORY_NAME)) {
            actions.moveToElement(dressesCategory.get(1)).build().perform();
        } else if (categoryName.equalsIgnoreCase(WOMEN_CATEGORY_NAME)) {
            actions.moveToElement(womenCategory).build().perform();
        }
    }

    public List<String> createListOfAllSubCategories() {
        List<String> list = new ArrayList<>();
        root.getWomen().getDresses().forEach(x -> list.add(x.getName()));
        root.getWomen().getTops().forEach(x -> list.add(x.getName()));
        return list;
    }
    }




