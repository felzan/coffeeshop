package com.felzan.coffeeshop.adapters.mysql.user;

import static javax.persistence.CascadeType.PERSIST;
import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.order.OrderEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "user")
@Entity(name = "user")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity {

  String username;
  String password;
  @Email
  String email;
  @OneToMany(mappedBy = "user")
  List<OrderEntity> orders;
  @ManyToOne(cascade = PERSIST)
  @JoinColumn(name = "whitelabel_id")
  WhiteLabelEntity whiteLabel;

}
