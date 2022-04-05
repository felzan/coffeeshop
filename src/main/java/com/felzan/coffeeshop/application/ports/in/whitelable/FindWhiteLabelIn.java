package com.felzan.coffeeshop.application.ports.in.whitelable;

import com.felzan.coffeeshop.application.models.WhiteLabel;

public interface FindWhiteLabelIn {

  WhiteLabel findById(Long id);
}
