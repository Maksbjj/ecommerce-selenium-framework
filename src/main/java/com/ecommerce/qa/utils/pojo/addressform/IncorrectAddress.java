package com.ecommerce.qa.utils.pojo.addressform;

import lombok.Data;

@Data
public class IncorrectAddress {
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String additionalInfoMessage;
    private String addressTitle;
    private String alert;
}
