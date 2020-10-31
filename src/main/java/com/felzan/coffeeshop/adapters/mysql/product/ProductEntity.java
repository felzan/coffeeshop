package com.felzan.coffeeshop.adapters.mysql.product;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.application.models.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {

    private String name;
    private String description;
    private boolean available;
    private String image;
    private Integer price;
    private String sku;
    @ManyToMany(targetEntity = CategoryEntity.class, mappedBy = "products", fetch = LAZY)
    private List<CategoryEntity> categories;

    public ProductEntity(Product product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setAvailable(product.isAvailable());
        setImage(product.getImage());
        setPrice(product.getPrice());
        setSku(product.getSku());
    }

    public Product toProduct() {
        return Product.builder()
                .id(getId())
                .name(getName())
                .description(getDescription())
                .available(isAvailable())
                .image(getImage())
                .price(getPrice())
                .sku(getSku())
                .build();
    }
}
