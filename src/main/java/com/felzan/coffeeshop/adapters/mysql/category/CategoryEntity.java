package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.application.models.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity(name = "category")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CategoryEntity extends BaseEntity {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    @ManyToMany(cascade = {MERGE, PERSIST}, fetch = LAZY)
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<ProductEntity> products;

    public CategoryEntity(Category category) {
        List<ProductEntity> products = category.getProducts().stream()
                .map(ProductEntity::new)
                .collect(Collectors.toList());

        setId(category.getId());
        setName(category.getName());
        setDescription(category.getDescription());
        setImage(category.getImage());
        setStatus(category.getStatus());
        setVisible(category.isVisible());
        setProducts(products);
    }

}
