package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.in.whitelable.CreateWhiteLabelIn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class WhiteLabelService implements CreateWhiteLabelIn {

//  SaveWhiteLabel saveWhiteLabel;

  @Override
  public WhiteLabel save(WhiteLabelDTO dto) {
//    var model = dto.toModel();
//    return saveWhiteLabel.save(model);
    return dto.toModel();
  }
}
