package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;
import com.felzan.coffeeshop.application.ports.in.CreateCategory;
import com.felzan.coffeeshop.application.ports.in.FindCategory;
import com.felzan.coffeeshop.application.ports.in.FindCategoryCriteria;
import com.felzan.coffeeshop.application.ports.in.UpdateCategory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.felzan.coffeeshop.adapters.web.admin.ConstantsController.ADMIN_CATEGORY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = ADMIN_CATEGORY, produces = APPLICATION_JSON_VALUE)
public class AdminCategoryController {

    FindCategory findCategory;
    CreateCategory createCategory;
    UpdateCategory updateCategory;

    @GetMapping(value = "")
    public ResponseEntity<Iterable<CategoryEntity>> get(@RequestBody(required = false) FindCategoryCriteria criteria) {
        return ResponseEntity.ok(findCategory.find(criteria));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public void post(@RequestBody CreateCategoryRequest category) {
        createCategory.create(category);
    }

    @PutMapping(value = "{categoryId}", consumes = APPLICATION_JSON_VALUE)
    public void put(@RequestBody CreateCategoryRequest category,
                    @PathVariable("categoryId") Long categoryId) {
        updateCategory.update(categoryId, category);
    }
}
