package com.ecommerce.qa.pageobjects.myaccount;

import com.ecommerce.qa.pageobjects.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyAddressesPage extends HomePage {

    public MyAddressesPage(){
        super();
    }

    @FindBy(css ="div[class*='addresses'] h3[class*='page-subheading']")
    private List<WebElement> addressHeadingsList;


    public boolean isAddressExist(String addressTitle) {
        for (WebElement webElement : addressHeadingsList) {
            if(webElement.getText().equalsIgnoreCase(addressTitle)) {
                return true;
            }
        }
        return false;
    }
}
