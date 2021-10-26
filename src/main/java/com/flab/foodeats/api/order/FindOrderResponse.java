package com.flab.foodeats.api.order;

import com.flab.foodeats.domain.order.Order;
import com.flab.foodeats.domain.order.OrderMenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindOrderResponse {

	private long orderNo;
	private String orderStatus;
	private int orderPrice;

	private OrderMenu orderMenu;
	private OrderMenu orderMenu1;

	private long consumerNo;
	private String consumerId;
	private String consumerName;

	private long shopNo;
	private String shopBrand;
	private String shopCategory;

	public static FindOrderResponse of(Order order) {
		return FindOrderResponse.builder()
			.orderNo(order.getOrderNo())
			.orderStatus(order.getOrderStatus())
			.orderPrice(order.getTotalPrice())

			.orderMenu(order.getOrderMenu())
			.orderMenu1(order.getOrderMenu1())

			.consumerNo(order.getConsumerInfo().getId())
			.consumerId(order.getConsumerInfo().getUserId())
			.consumerName(order.getConsumerInfo().getName())

			.shopNo(order.getShopInfo().getShopId())
			.shopBrand(order.getShopInfo().getBrand())
			.shopCategory(order.getShopInfo().getCategory())
			.build();
	}
}

