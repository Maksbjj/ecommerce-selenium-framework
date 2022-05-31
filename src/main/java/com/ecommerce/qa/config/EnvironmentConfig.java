package com.ecommerce.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:EnvironmentConfig.properties")
public interface EnvironmentConfig extends Config {

    @Key("base_url")
    String getBaseUrl();

    @Key("user_email")
    String getUserEmail();

    @Key("user_pass")
    String getUserPassword();

}
