package com.flab.foodeats.infra.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.foodeats.domain.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
