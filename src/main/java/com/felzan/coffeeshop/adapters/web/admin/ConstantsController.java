package com.felzan.coffeeshop.adapters.web.admin;

public interface ConstantsController {

    String BASE = "api/v1";
    String ADMIN = "admin";
    String CATEGORY = "categories";
    String PRODUCT = "products";

    String ADMIN_CATEGORY = BASE + "/" + ADMIN + "/" + CATEGORY;
    String ADMIN_PRODUCT = BASE + "/" + ADMIN + "/" + PRODUCT;

}

