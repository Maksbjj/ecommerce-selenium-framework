package com.ecommerce.qa.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends HomePage {

    public ProductPage() {
        super();
    }

    @FindBy(css ="h1[itemprop='name']")
    private WebElement productName;


    public String getProductName(){
        return getElementsText(productName);
    }
}
