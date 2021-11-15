package com.flab.foodeats.application.order.port;

import com.flab.foodeats.api.order.FindAllOrderResponse;
import com.flab.foodeats.api.order.FindOrderResponse;
import com.flab.foodeats.application.order.RegisterOrderTarget;

public interface OrderService {

	FindOrderResponse registerOrder(RegisterOrderTarget target);
	void cancleOrder(long userId, long orderId);
	FindOrderResponse findOrderByOrderId(long userId, long orderId);
	FindAllOrderResponse findAllOrderByUserId(long userId);

}
