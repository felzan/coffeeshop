package com.felzan.coffeeshop.adapters.web.whitelabel;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.WHITE_LABEL;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.whitelabel.requestbody.WhiteLabelRequest;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.in.whitelable.CreateWhiteLabelIn;
import com.felzan.coffeeshop.application.ports.in.whitelable.FindWhiteLabelIn;
import com.felzan.coffeeshop.application.ports.in.whitelable.UpdateWhiteLabelIn;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = WHITE_LABEL, produces = APPLICATION_JSON_VALUE)
public class WhiteLabelController {

  CreateWhiteLabelIn createWhiteLabelIn;
  FindWhiteLabelIn findWhiteLabelIn;
  UpdateWhiteLabelIn updateWhiteLabelIn;
  BeanMapper beanMapper;

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<WhiteLabel> create(@RequestBody WhiteLabelRequest request) {
    var saved = createWhiteLabelIn.save(beanMapper.whiteLabelRequestToDTO(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<WhiteLabel> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(findWhiteLabelIn.findById(id));
  }

  @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<WhiteLabel> update(@PathVariable Long id,
      @RequestBody WhiteLabelRequest request) {
    WhiteLabel updated = updateWhiteLabelIn.update(id, beanMapper.whiteLabelRequestToDTO(request));
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }
}
