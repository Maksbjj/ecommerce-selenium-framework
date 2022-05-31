package com.ecommerce.qa.util;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    Faker faker = new Faker();

    public String validEmail() {
        return faker.internet().emailAddress();
    }
    public String invalidEmail() {
        return faker.name().toString();
    }
    public String randomPassword(){
        return  faker.internet().password();
    }
}
