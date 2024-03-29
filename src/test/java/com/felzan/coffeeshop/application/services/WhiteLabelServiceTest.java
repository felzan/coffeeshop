package com.felzan.coffeeshop.application.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.out.SaveWhiteLabel;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
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
class WhiteLabelServiceTest {

  @InjectMocks
  private WhiteLabelService whiteLabelService;
  @Mock
  private SaveWhiteLabel saveWhiteLabel;

  @BeforeEach
  void setup() {
    BeanMapper beanMapper = Mappers.getMapper(BeanMapper.class);
    ReflectionTestUtils.setField(whiteLabelService, "beanMapper", beanMapper);
  }

  @Test
  @DisplayName("Should save")
  void save() {
    var input = buildWhiteLabelDTO();
    var whiteLabel = buildWhiteLabel();
    when(saveWhiteLabel.save(any()))
        .thenReturn(whiteLabel);
    whiteLabelService.save(input);
  }

  private WhiteLabel buildWhiteLabel() {
    return WhiteLabel.builder()
        .id(1L)
        .name("Test Merchant")
        .description("Merchant for test")
        .build();
  }

  private WhiteLabelDTO buildWhiteLabelDTO() {
    return WhiteLabelDTO.builder()
        .name("Test Merchant")
        .description("Merchant for test")
        .build();
  }
}