package com.felzan.coffeeshop.adapters.web;

public interface ConstantsController {

  String BASE = "/api/v1";
  String ADMIN = "admin";
  String CATEGORY = "categories";
  String PRODUCT = "products";

  String USER = BASE + "/users";
  String CART = BASE + "/carts";
  String MERCHANT = BASE + "/merchants";
  String WHITE_LABEL = BASE + "/whitelabels";

  String ADMIN_CATEGORY = BASE + "/" + ADMIN + "/" + CATEGORY;
  String ADMIN_PRODUCT = BASE + "/" + ADMIN + "/" + PRODUCT;
  String ADMIN_CART = BASE + "/" + ADMIN + "/carts";
  String ADMIN_USER = BASE + "/" + ADMIN + "/users";

}

