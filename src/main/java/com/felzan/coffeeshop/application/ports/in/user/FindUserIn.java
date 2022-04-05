package com.felzan.coffeeshop.application.ports.in.user;

import com.felzan.coffeeshop.application.models.User;
import java.util.List;

public interface FindUserIn {

  List<User> findAll(Long whiteLabelId);

}
