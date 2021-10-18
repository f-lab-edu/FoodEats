package com.flab.foodeats.infra.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.shop.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}

