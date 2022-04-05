package com.felzan.coffeeshop.infrastructure.mapper;

import com.felzan.coffeeshop.adapters.mysql.cartitem.OrderItemEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantWorkingHourEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantWorkingHourShiftEntity;
import com.felzan.coffeeshop.adapters.mysql.order.OrderEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import com.felzan.coffeeshop.adapters.web.merchant.requestbody.MerchantRequest;
import com.felzan.coffeeshop.adapters.web.merchant.requestbody.MerchantWorkingHourRequest;
import com.felzan.coffeeshop.adapters.web.merchant.requestbody.MerchantWorkingHourShiftRequest;
import com.felzan.coffeeshop.adapters.web.user.requestbody.UserRequest;
import com.felzan.coffeeshop.adapters.web.whitelabel.requestbody.WhiteLabelRequest;
import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourShiftDTO;
import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.models.MerchantWorkingHour;
import com.felzan.coffeeshop.application.models.MerchantWorkingHourShift;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.models.OrderItem;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

@Configuration
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
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

  Merchant merchantDTOToModel(MerchantDTO s);

  MerchantDTO merchantRequestToDTO(MerchantRequest s);

  MerchantEntity merchantModelToEntity(Merchant s);

  // Product
  Product productEntityToModel(ProductEntity s);

  Product productDTOToModel(ProductDTO s);

  // Category
  Category categoryEntityToModel(CategoryEntity s);

  Category categoryDTOToModel(CategoryDTO s);

  // Order
  Order orderEntityToModel(OrderEntity s);

  Order orderDTOToModel(OrderDto s);

  // OrderItem
  OrderItem orderItemEntityToModel(OrderItemEntity s);

  OrderItem orderItemDTOToModel(OrderItemEntity s);

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "orderItems", ignore = true)
  @Mapping(target = "merchant", ignore = true)
  OrderEntity orderModelToEntity(Order s);

  // MerchantWorkingHour
  MerchantWorkingHour merchantWorkingHourEntityToModel(MerchantWorkingHourEntity s);

  MerchantWorkingHour merchantWorkingHourDTOToModel(MerchantWorkingHourDTO s);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  MerchantWorkingHourDTO merchantWorkingHourRequestToDTO(MerchantWorkingHourRequest s);

  @Mapping(target = "merchant", ignore = true)
  MerchantWorkingHourEntity merchantWorkingHourModelToEntity(MerchantWorkingHour s);

  // MerchantWorkingHourShift
  MerchantWorkingHourShift merchantWorkingHourShiftEntityToModel(MerchantWorkingHourShiftEntity s);

  MerchantWorkingHourShift merchantWorkingHourShiftDTOToModel(MerchantWorkingHourShiftDTO s);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  MerchantWorkingHourShiftDTO merchantWorkingHourShiftRequestToDTO(
      MerchantWorkingHourShiftRequest s);

  @Mapping(target = "merchantWorkingHour", ignore = true)
  MerchantWorkingHourShiftEntity merchantWorkingHourShiftModelToEntity(MerchantWorkingHourShift s);

}
