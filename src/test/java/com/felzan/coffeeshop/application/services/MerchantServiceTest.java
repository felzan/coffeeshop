package com.felzan.coffeeshop.application.services;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.LocalTime.NOON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourDTO;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourShiftDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.models.MerchantWorkingHour;
import com.felzan.coffeeshop.application.models.MerchantWorkingHourShift;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.out.FindMerchant;
import com.felzan.coffeeshop.application.ports.out.FindWhiteLabel;
import com.felzan.coffeeshop.application.ports.out.SaveMerchant;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MerchantServiceTest {

  @InjectMocks
  private MerchantService merchantService;
  @Mock
  private SaveMerchant saveMerchant;
  @Mock
  private FindMerchant findMerchant;
  @Mock
  private FindWhiteLabel findWhiteLabel;

  @BeforeEach
  void setup() {
    BeanMapper beanMapper = Mappers.getMapper(BeanMapper.class);
    ReflectionTestUtils.setField(merchantService, "beanMapper", beanMapper);
  }

  @Test
  @DisplayName("Should save")
  void save() {

    var input = buildMerchantDTO();
    var merchant = buildMerchant();

//    when(beanMapper.merchantDTOToModel(input))
//        .thenReturn(merchant);
    when(findWhiteLabel.findById(anyLong()))
        .thenReturn(Optional.of(merchant.getWhiteLabel()));
    when(saveMerchant.save(any()))
        .thenReturn(merchant);

    merchantService.save(input);
  }

  @Test
  @DisplayName("FindById")
  void findById() {
    var merchant = Optional.of(buildMerchant());
    when(findMerchant.findById(any()))
        .thenReturn(merchant);
    merchantService.findById(1L);
  }

  private Merchant buildMerchant() {
    var whiteLabel = WhiteLabel.builder()
        .id(1L)
        .build();
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
        .whiteLabel(whiteLabel)
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
        .whiteLabelId(1L)
        .workingHours(Collections.singletonList(workingHours))
        .build();
  }
}