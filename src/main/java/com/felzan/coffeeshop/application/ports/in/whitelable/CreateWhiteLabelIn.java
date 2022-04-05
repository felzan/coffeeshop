package com.felzan.coffeeshop.application.ports.in.whitelable;

import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.WhiteLabel;

public interface CreateWhiteLabelIn {

  WhiteLabel save(WhiteLabelDTO input);
}
