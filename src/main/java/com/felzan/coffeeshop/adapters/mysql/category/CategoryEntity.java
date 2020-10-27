package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.application.models.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity(name = "category")
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    @ManyToMany(cascade = {MERGE, PERSIST,DETACH})
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<ProductEntity> products;

    public CategoryEntity(Category category) {
        setId(category.getId());
        this.name = category.getName();
        this.description = category.getDescription();
        this.image = category.getImage();
        this.status = category.getStatus();
        this.visible = category.isVisible();
    }
}
