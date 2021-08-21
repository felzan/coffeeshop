package com.felzan.coffeeshop.adapters.mysql.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
