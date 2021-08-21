package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.in.whitelable.CreateWhiteLabelIn;
import com.felzan.coffeeshop.application.ports.in.whitelable.FindWhiteLabelIn;
import com.felzan.coffeeshop.application.ports.in.whitelable.UpdateWhiteLabelIn;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.FindWhiteLabel;
import com.felzan.coffeeshop.application.ports.out.SaveWhiteLabel;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class WhiteLabelService implements CreateWhiteLabelIn, FindWhiteLabelIn, UpdateWhiteLabelIn {

  SaveWhiteLabel saveWhiteLabel;
  FindWhiteLabel findWhiteLabel;
  FindUser findUser;
  BeanMapper beanMapper;

  @Override
  public WhiteLabel save(WhiteLabelDTO dto) {
    return saveWhiteLabel.save(beanMapper.whiteLabelDTOToModel(dto));
  }

  @Override
  public WhiteLabel findById(Long id) {
    return findWhiteLabel.findById(id).orElseThrow();
  }

  //FIXME: not working
  @Override
  public WhiteLabel update(Long id, WhiteLabelDTO dto) {
    WhiteLabel savedWhiteLabel = findWhiteLabel.findById(id).orElseThrow();
    WhiteLabel model = beanMapper.whiteLabelDTOToModel(dto);
    savedWhiteLabel.setUsers(model.getUsers());
    return saveWhiteLabel.save(savedWhiteLabel);
  }
}
