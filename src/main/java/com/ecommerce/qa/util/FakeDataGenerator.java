package com.ecommerce.qa.util;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    private static final Faker faker = new Faker();

    public static String validEmail() {
        return faker.internet().emailAddress();
    }

    public static String invalidEmail() {
        return faker.name().toString();
    }

    public static String randomPassword() {
        return faker.internet().password();
    }

    public static String randomMessage() {
        return faker.regexify("[A-Za-z0-9]{20}");
    }

    public static String randomNumber() {
        return faker.number().toString();
    }
}
