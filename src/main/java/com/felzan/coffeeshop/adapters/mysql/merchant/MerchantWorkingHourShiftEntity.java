package com.felzan.coffeeshop.adapters.mysql.merchant;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.application.models.MerchantWorkingHourShift;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "merchant_working_hour_shift")
@Entity(name = "merchant_working_hour_shift")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantWorkingHourShiftEntity extends BaseEntity {

  LocalTime begin;
  LocalTime end;
  @ManyToOne
  @JoinColumn(name = "merchant_working_hour_id")
  MerchantWorkingHourEntity merchantWorkingHour;

  public MerchantWorkingHourShiftEntity(MerchantWorkingHourShift model) {
    setId(model.getId());
    setBegin(model.getBegin());
    setEnd(model.getEnd());
  }

  public MerchantWorkingHourShift toModel() {
    return MerchantWorkingHourShift.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .begin(begin)
        .end(end)
        .build();
  }
}
