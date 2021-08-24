package com.felzan.coffeeshop.adapters.mysql.whitelabel;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.out.FindWhiteLabel;
import com.felzan.coffeeshop.application.ports.out.SaveWhiteLabel;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class WhiteLabelDAO implements SaveWhiteLabel, FindWhiteLabel {

  WhiteLabelRepository whiteLabelRepository;
  BeanMapper mapper;

  @Override
  public WhiteLabel save(WhiteLabel model) {
    WhiteLabelEntity entity = whiteLabelRepository.save(mapper.whiteLabelModelToEntity(model));
    return mapper.whiteLabelEntityToModel(entity);
  }

  @Override
  @Transactional
  public Optional<WhiteLabel> findById(Long id) {
    return whiteLabelRepository.findById(id)
        .map(mapper::whiteLabelEntityToModel);
  }
}
