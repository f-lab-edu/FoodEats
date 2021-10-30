package com.flab.foodeats.infra.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.foodeats.domain.order.OrderMenuOption;

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Long> {

}
