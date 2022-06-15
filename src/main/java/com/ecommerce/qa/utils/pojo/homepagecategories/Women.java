package com.ecommerce.qa.utils.pojo.homepagecategories;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Women {
        private List<Category> categories = new ArrayList<>();
        private List<Top> tops = new ArrayList<>();
        private List<Dress> dresses = new ArrayList<>();
}
