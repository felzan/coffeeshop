package com.felzan.coffeeshop.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel {

    private String name;
    private String description;
    private String image;
    private String status;
    private boolean visible;
    private List<Product> products;

}
