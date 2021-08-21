package com.felzan.coffeeshop.infrastructure.mapper;

import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import com.felzan.coffeeshop.adapters.web.user.requestbody.UserRequest;
import com.felzan.coffeeshop.adapters.web.whitelabel.requestbody.WhiteLabelRequest;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BeanMapper {

  // User
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "token", ignore = true)
  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "whiteLabel", ignore = true)
  User userEntityToModel(UserEntity s);

  User userDTOToModel(UserDTO s);

  UserEntity userModelToEntity(User s);

  UserDTO userModelToDTO(User s);

  UserDTO userRequestToDTO(UserRequest s);

  // WhiteLabel
  @Mapping(target = "merchants", ignore = true)
  @Mapping(target = "users.merchant", ignore = true)
  WhiteLabel whiteLabelEntityToModel(WhiteLabelEntity s);

  @Mapping(target = "merchants", ignore = true)
  @Mapping(target = "users", ignore = true)
  WhiteLabel whiteLabelDTOToModel(WhiteLabelDTO s);

  WhiteLabelEntity whiteLabelModelToEntity(WhiteLabel s);

  WhiteLabelDTO whiteLabelModelToDTO(WhiteLabel s);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  WhiteLabelDTO whiteLabelRequestToDTO(WhiteLabelRequest s);

  // Merchant
  Merchant merchantEntityToModel(MerchantEntity s);
}
