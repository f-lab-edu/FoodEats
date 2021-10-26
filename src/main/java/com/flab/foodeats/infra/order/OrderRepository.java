package com.flab.foodeats.infra.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.foodeats.domain.order.Order;
import com.flab.foodeats.domain.user.Consumer;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// List<Order> findByUserNo(long userNo);
	List<Order> findByConsumerInfo(Consumer consumer);
}
