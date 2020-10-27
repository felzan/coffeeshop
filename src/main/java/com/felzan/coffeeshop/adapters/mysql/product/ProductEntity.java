package com.felzan.coffeeshop.adapters.mysql.product;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity(name = "product")
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;
    @ManyToMany(mappedBy = "products")
    List<CategoryEntity> categories;
}
