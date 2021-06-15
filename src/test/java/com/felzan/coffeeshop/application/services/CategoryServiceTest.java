package com.felzan.coffeeshop.application.services;

import static com.felzan.coffeeshop.application.services.fixture.CategoryDTOFixture.categoryDTOFixture;
import static com.felzan.coffeeshop.application.services.fixture.CategoryFixture.categoryFixture;
import static com.felzan.coffeeshop.application.services.fixture.CategoryFixture.categoryListFixture;
import static com.felzan.coffeeshop.application.services.fixture.ProductFixture.productListFixture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryCriteria;
import com.felzan.coffeeshop.application.ports.out.DeleteCategory;
import com.felzan.coffeeshop.application.ports.out.FindCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @InjectMocks
  private CategoryService categoryService;
  @Mock
  private FindCategory findCategory;
  @Mock
  private DeleteCategory deleteCategory;
  @Mock
  private SaveCategory saveCategory;
  @Mock
  private ProductService productService;

  @Test
  @DisplayName("Should create a category")
  void create() {
    CategoryDTO categoryDTO = categoryDTOFixture();
    when(productService.findAllByIds(categoryDTO.getProductsIds()))
        .thenReturn(productListFixture());

    categoryService.create(categoryDTO);

    verify(saveCategory, times(1)).save(any(Category.class));
  }

  @Test
  @DisplayName("Should update a category")
  void update() {
    Long categoryId = 99L;
    CategoryDTO categoryDTO = categoryDTOFixture();
    when(productService.findAllByIds(categoryDTO.getProductsIds()))
        .thenReturn(productListFixture());

    categoryService.update(categoryId, categoryDTO);

    verify(saveCategory, times(1)).save(any(Category.class));
  }

  @Test
  @DisplayName("Should call delete category")
  void delete() {
    Long categoryId = 1L;

    categoryService.delete(categoryId);

    verify(deleteCategory, times(1)).delete(categoryId);
  }

  @Test
  @DisplayName("Should return categories")
  void find() {
    FindCategoryCriteria findCategoryCriteria = new FindCategoryCriteria();
    when(findCategory.find(findCategoryCriteria))
        .thenReturn(categoryListFixture());

    categoryService.find(findCategoryCriteria);
  }

  @Test
  @DisplayName("Should return category")
  void findById() {
    Long categoryId = 99L;
    when(findCategory.findById(categoryId))
        .thenReturn(Optional.of(categoryFixture()));

    categoryService.findById(categoryId);
  }
}