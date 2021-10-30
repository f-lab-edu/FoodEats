package com.flab.foodeats.infra.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.foodeats.domain.order.OrderMenu;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {

}
