package com.felzan.coffeeshop.adapters.web.merchant.requestbody;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRequest {

  @NotBlank String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  @NotNull @Positive Long whiteLabelId;
  List<MerchantWorkingHourRequest> workingHours;

  public MerchantDTO toDTO() {
    var workingHoursList = workingHours.stream()
        .map(MerchantWorkingHourRequest::toDTO)
        .collect(Collectors.toList());
    return MerchantDTO.builder()
        .name(name)
        .description(description)
        .cnpj(cnpj)
        .latitude(latitude)
        .longitude(longitude)
        .workingHours(workingHoursList)
        .build();
  }
}
