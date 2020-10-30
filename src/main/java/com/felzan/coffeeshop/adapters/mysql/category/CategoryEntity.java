package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
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
    @ManyToMany(targetEntity = ProductEntity.class, cascade = ALL, fetch = LAZY)
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

    public Category toCategory() {
        List<Product> products = getProducts().stream()
                .map(ProductEntity::toProduct)
                .collect(Collectors.toList());

        return Category.builder()
                .id(getId())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .name(getName())
                .description(getDescription())
                .image(getImage())
                .status(getStatus())
                .visible(isVisible())
                .products(products)
                .build();
    }

}
