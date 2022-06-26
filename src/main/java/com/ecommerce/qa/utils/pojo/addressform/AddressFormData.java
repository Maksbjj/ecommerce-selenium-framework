package com.ecommerce.qa.utils.pojo.addressform;

import lombok.Data;

import java.util.List;

@Data
public class AddressFormData {
    private List<CorrectAddress> correct;
    private List<IncorrectAddress> incorrect;
}
