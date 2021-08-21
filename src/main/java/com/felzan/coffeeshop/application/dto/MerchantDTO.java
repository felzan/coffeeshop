package com.felzan.coffeeshop.application.dto;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Merchant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantDTO extends BaseDTO {

  String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  @Builder.Default
  List<MerchantWorkingHourDTO> workingHours = new ArrayList<>();

  public Merchant toModel() {
    var workingHoursList = workingHours.stream()
        .map(MerchantWorkingHourDTO::toModel)
        .collect(Collectors.toList());
    return Merchant.builder()
        .name(name)
        .description(description)
        .cnpj(cnpj)
        .latitude(latitude)
        .longitude(longitude)
        .workingHours(workingHoursList)
        .build();
  }
}
