package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateProductRequest;
import com.felzan.coffeeshop.adapters.web.admin.requestbody.UpdateProductRequest;
import com.felzan.coffeeshop.application.ports.in.product.CreateProductIn;
import com.felzan.coffeeshop.application.ports.in.product.DeleteProductIn;
import com.felzan.coffeeshop.application.ports.in.product.UpdateProductIn;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static com.felzan.coffeeshop.adapters.web.admin.ConstantsController.ADMIN_PRODUCT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = ADMIN_PRODUCT, produces = APPLICATION_JSON_VALUE)
public class AdminProductController {

    CreateProductIn createProductIn;
    UpdateProductIn updateProductIn;
    DeleteProductIn deleteProductIn;

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
