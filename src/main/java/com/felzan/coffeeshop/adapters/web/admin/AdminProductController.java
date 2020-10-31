package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateProductRequest;
import com.felzan.coffeeshop.adapters.web.admin.requestbody.UpdateProductRequest;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.product.CreateProductIn;
import com.felzan.coffeeshop.application.ports.in.product.DeleteProductIn;
import com.felzan.coffeeshop.application.ports.in.product.FindProductIn;
import com.felzan.coffeeshop.application.ports.in.product.UpdateProductIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.felzan.coffeeshop.adapters.web.admin.ConstantsController.ADMIN_PRODUCT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ADMIN_PRODUCT, produces = APPLICATION_JSON_VALUE)
public class AdminProductController {

    private final CreateProductIn createProductIn;
    private final DeleteProductIn deleteProductIn;
    private final FindProductIn findProductIn;
    private final UpdateProductIn updateProductIn;

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
