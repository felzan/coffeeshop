package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.in.merchant.CreateMerchantIn;
import com.felzan.coffeeshop.application.ports.in.merchant.FindMerchantIn;
import com.felzan.coffeeshop.application.ports.out.FindMerchant;
import com.felzan.coffeeshop.application.ports.out.FindWhiteLabel;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MerchantService implements CreateMerchantIn, FindMerchantIn {

  SaveMerchant saveMerchant;
  FindMerchant findMerchant;
  FindWhiteLabel findWhiteLabel;
  BeanMapper beanMapper;

  public Merchant save(MerchantDTO dto) {
    var model = beanMapper.merchantDTOToModel(dto);
    WhiteLabel whiteLabel = findWhiteLabel.findById(dto.getWhiteLabelId()).orElseThrow();
    model.setWhiteLabel(whiteLabel);
    return saveMerchant.save(model);
  }

  @Override
  public Merchant findById(Long id) {
    return findMerchant.findById(id).orElseThrow();
  }
}
