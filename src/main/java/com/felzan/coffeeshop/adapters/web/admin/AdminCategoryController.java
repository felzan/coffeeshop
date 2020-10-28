package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;
import com.felzan.coffeeshop.application.ports.in.category.*;
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
    CreateCategoryIn createCategoryIn;
    UpdateCategoryIn updateCategoryIn;
    DeleteCategoryIn deleteCategoryIn;

    @GetMapping(value = "")
    public ResponseEntity<Iterable<CategoryEntity>> get(@RequestBody(required = false) FindCategoryCriteria criteria) {
        return ResponseEntity.ok(findCategory.find(criteria));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public void post(@RequestBody CreateCategoryRequest category) {
        createCategoryIn.create(category.toCategoryDTO());
    }

    @PutMapping(value = "{categoryId}", consumes = APPLICATION_JSON_VALUE)
    public void put(@RequestBody CreateCategoryRequest category,
                    @PathVariable("categoryId") Long categoryId) {
        updateCategoryIn.update(categoryId, category.toCategoryDTO());
    }

    @DeleteMapping(value = "{categoryId}")
    public void delete(@PathVariable("categoryId") Long categoryId) {
        deleteCategoryIn.delete(categoryId);
    }
}
