package com.felzan.coffeeshop.adapters.partners.adyen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethod {

  private String type;
  @JsonProperty("encryptedCardNumber")
  private String number;
  @JsonProperty("encryptedExpiryMonth")
  private String expiryMonth;
  @JsonProperty("encryptedExpiryYear")
  private String expiryYear;
  private String holderName;
  @JsonProperty("encryptedSecurityCode")
  private String cvc;
  private String storedPaymentMethodId;
  @JsonProperty("cardDetails.cvc")
  private String tokenCvc;

}
