package com.flab.foodeats.api.order;

import java.util.ArrayList;
import java.util.List;

import com.flab.foodeats.domain.order.Order;
import com.flab.foodeats.domain.order.OrderMenu;
import com.flab.foodeats.domain.order.OrderMenuOption;

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

	private List<OrderMenu> orderMenu;
	//private OrderMenu orderMenu1;

	private long consumerNo;
	private String consumerId;
	private String consumerName;

	private long shopNo;
	private String shopBrand;
	private String shopCategory;


	public static FindOrderResponse of(Order order) {

		List<OrderMenu> orderMenuList = new ArrayList<>();

		for (int i = 0; i < order.getOrderMenuList().size() ; i++){
			List<OrderMenuOption> orderMenuOptionList = new ArrayList<>();

			for (int j = 0; j < order.getOrderMenuList().get(i).getOrderMenuOptionList().size() ; j++){
				OrderMenuOption orderMenuOption = OrderMenuOption.builder()
					.menuOptionOrderNo(order.getOrderMenuList().get(i).getOrderMenuOptionList().get(j).getMenuOptionOrderNo())
					.menuOptionNo(order.getOrderMenuList().get(i).getOrderMenuOptionList().get(j).getMenuOptionNo())
					.menuOptionName(order.getOrderMenuList().get(i).getOrderMenuOptionList().get(j).getMenuOptionName())
					.menuOptionPrice(order.getOrderMenuList().get(i).getOrderMenuOptionList().get(j).getMenuOptionPrice())
					.build();

				orderMenuOptionList.add(orderMenuOption);
			}

			OrderMenu orderMenu = OrderMenu.builder()
				.menuOrderNo(order.getOrderMenuList().get(i).getMenuOrderNo())
				.menuNo(order.getOrderMenuList().get(i).getMenuNo())
				.menuName(order.getOrderMenuList().get(i).getMenuName())
				.menuPrice(order.getOrderMenuList().get(i).getMenuPrice())
				.orderMenuOptionList(orderMenuOptionList)
				.build();
			orderMenuList.add(orderMenu);
		}




		return FindOrderResponse.builder()
			.orderNo(order.getOrderNo())
			.orderStatus(order.getOrderStatus())
			.orderPrice(order.getTotalPrice())

			.orderMenu(orderMenuList)

			.consumerNo(order.getConsumerInfo().getId())
			.consumerId(order.getConsumerInfo().getUserId())
			.consumerName(order.getConsumerInfo().getName())

			.shopNo(order.getShopInfo().getShopId())
			.shopBrand(order.getShopInfo().getBrand())
			.shopCategory(order.getShopInfo().getCategory())
			.build();
	}

}
