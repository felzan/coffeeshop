package com.felzan.coffeeshop.adapters.mysql.whitelabel;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import java.util.List;
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
@Table(name = "whitelabel")
@Entity(name = "whitelabel")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class WhiteLabelEntity extends BaseEntity {

  String name;
  String description;
  @OneToMany(mappedBy = "whiteLabel")
  List<MerchantEntity> merchants;
  @OneToMany(mappedBy = "whiteLabel")
  List<UserEntity> users;

}
