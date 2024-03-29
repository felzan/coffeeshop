package com.felzan.coffeeshop.adapters.mysql.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  OrderEntity findTopByUser_Id(Long userId);
}
