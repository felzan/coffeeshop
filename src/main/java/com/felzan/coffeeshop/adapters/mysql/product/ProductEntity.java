package com.felzan.coffeeshop.adapters.mysql.product;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.application.models.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity(name = "product")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;
    @ManyToMany(mappedBy = "products", fetch = LAZY)
    List<CategoryEntity> categories;

    public ProductEntity(Product product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setAvailable(product.isAvailable());
        setImage(product.getImage());
        setPrice(product.getPrice());
        setSku(product.getSku());
    }
}
