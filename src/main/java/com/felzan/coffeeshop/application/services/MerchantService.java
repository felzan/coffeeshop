package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.ports.in.merchant.CreateMerchantIn;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MerchantService implements CreateMerchantIn {

  SaveMerchant saveMerchant;

  public Merchant save(MerchantDTO dto) {
    var model = dto.toModel();
    return saveMerchant.save(model);
  }
}
