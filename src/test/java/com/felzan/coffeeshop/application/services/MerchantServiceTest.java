package com.felzan.coffeeshop.application.services;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.LocalTime.NOON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourShiftDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.models.MerchantWorkingHour;
import com.felzan.coffeeshop.application.models.MerchantWorkingHourShift;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MerchantServiceTest {

  @InjectMocks
  private MerchantService merchantService;
  @Mock
  private SaveMerchant saveMerchant;

  @Test
  @DisplayName("Should save")
  void save() {
    var input = buildMerchantDTO();
    var merchant = buildMerchant();
    when(saveMerchant.save(any()))
        .thenReturn(merchant);
    merchantService.save(input);
  }

  private Merchant buildMerchant() {
    var shift = MerchantWorkingHourShift.builder()
        .id(1L)
        .begin(NOON)
        .end(MIDNIGHT)
        .build();
    var workingHours = MerchantWorkingHour.builder()
        .id(1L)
        .days(Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY))
        .description("Weekdays")
        .shifts(Collections.singletonList(shift))
        .build();
    return Merchant.builder()
        .id(1L)
        .name("Test Merchant")
        .description("Merchant for test")
        .cnpj("00000000000000")
        .latitude(-31.0)
        .longitude(-51.0)
        .workingHours(Collections.singletonList(workingHours))
        .build();
  }

  private MerchantDTO buildMerchantDTO() {
    var shiftDTO = MerchantWorkingHourShiftDTO.builder()
        .begin(NOON)
        .end(MIDNIGHT)
        .build();
    var workingHours = MerchantWorkingHourDTO.builder()
        .days(Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY))
        .description("Weekdays")
        .shifts(Collections.singletonList(shiftDTO))
        .build();
    return MerchantDTO.builder()
        .name("Test Merchant")
        .description("Merchant for test")
        .cnpj("00000000000000")
        .latitude(-31.0)
        .longitude(-51.0)
        .workingHours(Collections.singletonList(workingHours))
        .build();
  }
}