package com.ecommerce.qa.util.pojo;

import lombok.Data;

@Data
public class Alerts {
    private String newsletterSuccess;
    private String newsletterRegistered;
    private String newsletterInvalidEmail;
    private String productSuccess;
    private String invalidEmail;
    private String noAccount;
    private String confirmationSuccess;
    private String emailRequired;
    private String authFailed;
    private String passwordRequired;
    private String contactSuccess;
    private String selectSubjectError;
    private String blankMessageError;
    private String cartEmpty;
}
