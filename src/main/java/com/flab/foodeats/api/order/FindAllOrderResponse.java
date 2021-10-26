package com.flab.foodeats.api.order;

import java.util.ArrayList;
import java.util.List;

import com.flab.foodeats.domain.order.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllOrderResponse {

	private long consumerNo;
	private String consumerId;
	private String consumerName;

	private List<MenuListOfResponse> orderMenuList;

	public static FindAllOrderResponse ofList(List<Order> orderList) {
		List<MenuListOfResponse> list = new ArrayList<>();
		// todo. 반환 가맹점 정보

		for (int i = 0 ; i < orderList.size() ; i++){
			MenuListOfResponse menuListOfResponse = MenuListOfResponse.builder()
				.orderNo(orderList.get(i).getOrderNo())

				.shopNo(orderList.get(i).getShopInfo().getShopId())
				.shopBrand(orderList.get(i).getShopInfo().getBrand())
				.shopCategory(orderList.get(i).getShopInfo().getCategory())

				.orderMenu(orderList.get(i).getOrderMenu())
				.orderMenu1(orderList.get(i).getOrderMenu1())
				.orderStatus(orderList.get(i).getOrderStatus())
				.totalPrice(orderList.get(i).getTotalPrice())
				.build();

			list.add(menuListOfResponse);
		}

		return FindAllOrderResponse.builder()
			.orderMenuList(list)
			.consumerNo(orderList.get(0).getConsumerInfo().getId())
			.consumerId(orderList.get(0).getConsumerInfo().getUserId())
			.consumerName(orderList.get(0).getConsumerInfo().getName())
			.build();
	}
}

