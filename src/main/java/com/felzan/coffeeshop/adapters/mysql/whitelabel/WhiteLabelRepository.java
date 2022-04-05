package com.felzan.coffeeshop.adapters.mysql.whitelabel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteLabelRepository extends JpaRepository<WhiteLabelEntity, Long> {

}
