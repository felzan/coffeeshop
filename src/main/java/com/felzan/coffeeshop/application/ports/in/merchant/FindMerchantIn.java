package com.felzan.coffeeshop.application.ports.in.merchant;

import com.felzan.coffeeshop.application.models.Merchant;

public interface FindMerchantIn {

  Merchant findById(Long id);
}
