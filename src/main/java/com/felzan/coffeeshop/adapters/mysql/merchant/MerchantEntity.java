package com.felzan.coffeeshop.adapters.mysql.merchant;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.application.models.Merchant;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchant")
@Entity(name = "merchant")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantEntity extends BaseEntity {

  String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  @OneToMany(mappedBy = "merchant")
  List<MerchantWorkingHourEntity> workingHours;

  public MerchantEntity(Merchant model) {
    var workingHourList = model.getWorkingHours().stream()
        .map(MerchantWorkingHourEntity::new)
        .collect(Collectors.toList());

    setName(model.getName());
    setDescription(model.getDescription());
    setCnpj(model.getCnpj());
    setLatitude(model.getLatitude());
    setLongitude(model.getLongitude());
    setWorkingHours(workingHourList);
  }

  public Merchant toModel() {
    var workingHoursList = workingHours.stream()
        .map(MerchantWorkingHourEntity::toModel)
        .collect(Collectors.toList());
    return Merchant.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .name(name)
        .description(description)
        .cnpj(cnpj)
        .latitude(latitude)
        .longitude(longitude)
        .workingHours(workingHoursList)
        .build();
  }

}
