package com.felzan.coffeeshop.adapters.web.admin;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.ADMIN_CATEGORY;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.in.category.CreateCategoryIn;
import com.felzan.coffeeshop.application.ports.in.category.DeleteCategoryIn;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryCriteria;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryIn;
import com.felzan.coffeeshop.application.ports.in.category.UpdateCategoryIn;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = ADMIN_CATEGORY, produces = APPLICATION_JSON_VALUE)
public class AdminCategoryController {

  FindCategoryIn findCategoryIn;
  CreateCategoryIn createCategoryIn;
  UpdateCategoryIn updateCategoryIn;
  DeleteCategoryIn deleteCategoryIn;

  @GetMapping(value = "")
  public ResponseEntity<List<Category>> get(
      @RequestBody(required = false) FindCategoryCriteria criteria) {
    return ResponseEntity.ok(findCategoryIn.find(criteria));
  }

  @GetMapping(value = "{categoryId}")
  public ResponseEntity<Category> get(@PathVariable("categoryId") Long categoryId) {
    return ResponseEntity.ok(findCategoryIn.findById(categoryId));
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

  @PatchMapping(value = "{categoryId}", consumes = APPLICATION_JSON_VALUE)
  public void patch(@RequestBody CreateCategoryRequest category,
      @PathVariable("categoryId") Long categoryId) {
    updateCategoryIn.update(categoryId, category.toCategoryDTO());
  }

  @DeleteMapping(value = "{categoryId}")
  public void delete(@PathVariable("categoryId") Long categoryId) {
    deleteCategoryIn.delete(categoryId);
  }
}
