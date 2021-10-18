package com.flab.foodeats.infra.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.shop.ShopDelivery;

@Repository
public interface ShopDeliveryRepository extends JpaRepository<ShopDelivery, Long> {

}

