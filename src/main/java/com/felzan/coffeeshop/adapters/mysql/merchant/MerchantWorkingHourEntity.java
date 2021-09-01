package com.felzan.coffeeshop.adapters.mysql.merchant;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.application.models.MerchantWorkingHour;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "merchant_working_hour")
@Entity(name = "merchant_working_hour")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantWorkingHourEntity extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @ElementCollection(targetClass = DayOfWeek.class)
  Set<DayOfWeek> days;
  String description;
  @ManyToOne(cascade = ALL)
  @JoinColumn(name = "merchant_id")
  MerchantEntity merchant;
  @OneToMany(mappedBy = "merchantWorkingHour", cascade = ALL)
  List<MerchantWorkingHourShiftEntity> shifts;

  public MerchantWorkingHour toModel() {
    var shiftsList = shifts.stream()
        .map(MerchantWorkingHourShiftEntity::toModel)
        .collect(Collectors.toList());
    return MerchantWorkingHour.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .days(days)
        .description(description)
        .shifts(shiftsList)
        .build();
  }
}
