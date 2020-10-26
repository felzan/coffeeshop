package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.application.models.Category;
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
@Entity(name = "category")
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    @ManyToMany
    List<ProductEntity> products;

    public CategoryEntity(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.image = category.getImage();
        this.status = category.getStatus();
        this.visible = category.isVisible();
    }
}
