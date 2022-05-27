package com.ecommerce.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:EnvironmentConfig.properties")
public interface EnvironmentConfig extends Config {

    @Key("base_url")
    String getBaseUrl();

    @Key("page_title")
    String pageTitle();

    @Key("newsletter_alert_message")
    String newsletterAlertMessage();

    @Key("newsletter_alert_message_failed")
    String newsletterFailedMessage();

    @Key("newsletter_alert_message_invalid")
    String newsletterInvalidEmailMessage();

    @Key("newsletter_previously_user_email")
    String registeredEmail();

    @Key("add_to_product_cart_message")
    String addToCartMessage();

    @Key("forgot_password_invalid")
    String forgotPasswordInvalidMessage();

    @Key("forgot_password_no_account")
    String forgetPasswordNoAccountMessage();

    @Key("forgot_password_success")
    String forgotPasswordSuccessMessage();

}
