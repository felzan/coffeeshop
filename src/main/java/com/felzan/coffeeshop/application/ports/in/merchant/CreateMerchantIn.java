package com.felzan.coffeeshop.application.ports.in.merchant;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.models.Merchant;

public interface CreateMerchantIn {

  Merchant save(MerchantDTO input);
}
