package com.ecommerce.qa.base;

import com.ecommerce.qa.config.EnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;

public class EnvConfigContext {
    public static EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
}
