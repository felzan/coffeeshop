package com.felzan.coffeeshop.adapters.web.merchant;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.MERCHANT;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.merchant.requestbody.MerchantRequest;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.ports.in.merchant.CreateMerchantIn;
import com.felzan.coffeeshop.application.ports.in.merchant.FindMerchantIn;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = MERCHANT, produces = APPLICATION_JSON_VALUE)
public class MerchantController {

  CreateMerchantIn createMerchantIn;
  FindMerchantIn findMerchantIn;
  BeanMapper beanMapper;

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Merchant> create(@RequestBody MerchantRequest request) {
    var saved = createMerchantIn.save(beanMapper.merchantRequestToDTO(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<Merchant> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(findMerchantIn.findById(id));
  }

}
