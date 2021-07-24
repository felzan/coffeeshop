package com.felzan.coffeeshop.adapters.mysql.merchant;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MerchantDAO implements SaveMerchant {

  MerchantRepository merchantRepository;

  @Override
  public Merchant save(Merchant merchant) {
    var merchantEntity = new MerchantEntity(merchant);
    return merchantRepository.save(merchantEntity).toModel();
  }
}
