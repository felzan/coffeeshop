package com.felzan.coffeeshop.adapters.web.admin;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.ADMIN_PRODUCT;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateProductRequest;
import com.felzan.coffeeshop.adapters.web.admin.requestbody.UpdateProductRequest;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.product.CreateProductIn;
import com.felzan.coffeeshop.application.ports.in.product.DeleteProductIn;
import com.felzan.coffeeshop.application.ports.in.product.FindProductIn;
import com.felzan.coffeeshop.application.ports.in.product.UpdateProductIn;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = ADMIN_PRODUCT, produces = APPLICATION_JSON_VALUE)
public class AdminProductController {

  CreateProductIn createProductIn;
  DeleteProductIn deleteProductIn;
  FindProductIn findProductIn;
  UpdateProductIn updateProductIn;

  @GetMapping(value = "")
  public ResponseEntity<List<Product>> get() {
    return ResponseEntity.ok(findProductIn.find());
  }

  @GetMapping(value = "{productId}")
  public ResponseEntity<Product> get(@PathVariable Long productId) {
    return ResponseEntity.ok(findProductIn.findById(productId));
  }

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public void post(@RequestBody CreateProductRequest productRequest) {
    createProductIn.create(productRequest.toDTO());
  }

  @PutMapping(value = "{productId}", consumes = APPLICATION_JSON_VALUE)
  public void put(@RequestBody UpdateProductRequest productRequest,
      @PathVariable Long productId) {
    updateProductIn.update(productId, productRequest.toDTO());
  }

  @DeleteMapping(value = "{productId}")
  public void delete(@PathVariable Long productId) {
    deleteProductIn.delete(productId);
  }

}
