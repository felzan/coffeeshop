package com.felzan.coffeeshop.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {

    private String name;
    private String description;
    private boolean available;
    private String image;
    private Integer price;
    private String sku;
}
