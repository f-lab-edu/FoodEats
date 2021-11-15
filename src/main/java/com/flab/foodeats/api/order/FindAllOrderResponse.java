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
public class FindAllOrderResponse {

	private long consumerNo;
	private String consumerId;
	private String consumerName;

	private List<MenuListOfResponse> orderMenuList;

	public static FindAllOrderResponse ofList(List<Order> orderList) {
		List<MenuListOfResponse> list = new ArrayList<>();
		// todo. 반환 가맹점 정보

		for (int i = 0 ; i < orderList.size() ; i++){


			List<OrderMenu> orderMenuList = new ArrayList<>();

			for (int k = 0; k < orderList.get(i).getOrderMenuList().size() ; k++){
				List<OrderMenuOption> orderMenuOptionList = new ArrayList<>();

				for (int j = 0; j < orderList.get(i).getOrderMenuList().get(k).getOrderMenuOptionList().size() ; j++){
					OrderMenuOption orderMenuOption = OrderMenuOption.builder()
						.menuOptionOrderNo(orderList.get(i).getOrderMenuList().get(k).getOrderMenuOptionList().get(j).getMenuOptionOrderNo())
						.menuOptionNo(orderList.get(i).getOrderMenuList().get(k).getOrderMenuOptionList().get(j).getMenuOptionNo())
						.menuOptionName(orderList.get(i).getOrderMenuList().get(k).getOrderMenuOptionList().get(j).getMenuOptionName())
						.menuOptionPrice(orderList.get(i).getOrderMenuList().get(k).getOrderMenuOptionList().get(j).getMenuOptionPrice())
						.build();

					orderMenuOptionList.add(orderMenuOption);
				}

				OrderMenu orderMenu = OrderMenu.builder()
					.menuOrderNo(orderList.get(i).getOrderMenuList().get(k).getMenuOrderNo())
					.menuNo(orderList.get(i).getOrderMenuList().get(k).getMenuNo())
					.menuName(orderList.get(i).getOrderMenuList().get(k).getMenuName())
					.menuPrice(orderList.get(i).getOrderMenuList().get(k).getMenuPrice())
					.orderMenuOptionList(orderMenuOptionList)
					.build();
				orderMenuList.add(orderMenu);
			}


			MenuListOfResponse menuListOfResponse = MenuListOfResponse.builder()
				.orderNo(orderList.get(i).getOrderNo())

				.shopNo(orderList.get(i).getShopInfo().getShopId())
				.shopBrand(orderList.get(i).getShopInfo().getBrand())
				.shopCategory(orderList.get(i).getShopInfo().getCategory())

				.orderMenu(orderMenuList)
				//.orderMenu1(orderList.get(i).getOrderMenu1())
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

