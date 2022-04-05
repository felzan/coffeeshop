package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Category;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CategoryDTO extends BaseDTO {

  String name;
  String description;
  String image;
  String status;
  boolean visible;
  List<Long> productsIds;

  public Category toCategory() {
    return Category.builder()
        .name(getName())
        .description(getDescription())
        .image(getImage())
        .status(getStatus())
        .visible(isVisible())
        .build();
  }
}
