package com.felzan.coffeeshop.adapters.mysql.merchant;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.ports.out.FindMerchant;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MerchantDAO implements SaveMerchant, FindMerchant {

  MerchantRepository merchantRepository;
  BeanMapper beanMapper;

  @Override
  public Merchant save(Merchant merchant) {
    MerchantEntity entity = beanMapper.merchantModelToEntity(merchant);
    entity.getWorkingHours().forEach(workingHour -> {
      workingHour.setMerchant(entity);
      workingHour.getShifts().forEach(shift -> shift.setMerchantWorkingHour(workingHour));
    });
    MerchantEntity saved = merchantRepository.save(entity);
    return beanMapper.merchantEntityToModel(saved);
  }

  @Override
  public Optional<Merchant> findById(Long id) {
    return merchantRepository.findById(id).map(beanMapper::merchantEntityToModel);
  }
}
