package com.ecommerce.qa.util;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    Faker faker = new Faker();

    public String validEmail() {
        return faker.internet().emailAddress();
    }

    public String invalidEmail() {
        return faker.name().toString();
    }
}
