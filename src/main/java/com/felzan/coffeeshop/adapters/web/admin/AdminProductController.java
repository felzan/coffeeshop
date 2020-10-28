package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateProductRequest;
import com.felzan.coffeeshop.application.ports.in.CreateProductIn;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.felzan.coffeeshop.adapters.web.admin.ConstantsController.ADMIN_PRODUCT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = ADMIN_PRODUCT, produces = APPLICATION_JSON_VALUE)
public class AdminProductController {

    CreateProductIn createProductIn;

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public void post(@RequestBody CreateProductRequest productRequest) {
        createProductIn.create(productRequest.toDTO());
    }

}
