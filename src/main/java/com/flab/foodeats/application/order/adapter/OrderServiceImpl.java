package com.flab.foodeats.application.order.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.api.order.FindAllOrderResponse;
import com.flab.foodeats.api.order.FindOrderResponse;
import com.flab.foodeats.api.order.MenuOptionListOfRequest;
import com.flab.foodeats.api.order.RegisterOrderRequest;
import com.flab.foodeats.application.order.RegisterOrderTarget;
import com.flab.foodeats.application.order.port.OrderService;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.order.Order;
import com.flab.foodeats.domain.order.OrderMenu;
import com.flab.foodeats.domain.order.OrderMenuOption;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.Merchant;
import com.flab.foodeats.infra.order.OrderMenuOptionRepository;
import com.flab.foodeats.infra.order.OrderMenuRepository;
import com.flab.foodeats.infra.order.OrderRepository;
import com.flab.foodeats.infra.shop.ShopRepository;
import com.flab.foodeats.infra.user.ConsumerRepository;
import com.flab.foodeats.infra.user.MerchantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMenuRepository orderMenuRepository;
	private final OrderMenuOptionRepository orderMenuOptionRepository;

	private final ShopRepository shopRepository;
	private final ConsumerRepository consumerRepository;

	@Override
	public FindOrderResponse registerOrder(RegisterOrderTarget target) {

		Consumer consumer = consumerRepository.findById(target.getUserNo())
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));

		// todo. merchant > shop 변환환
		Shop shop = shopRepository.findById(target.getShopNo())
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));

		Order order = new Order();
		order.setConsumerInfo(consumer);
		order.setShopInfo(shop);
		int totalOrderPrice = saveOrderList(order, target);
		order.changeTotalPrice(totalOrderPrice);

		Order responseOrderInfo = orderRepository.save(order);

		return FindOrderResponse.of(responseOrderInfo);
	}

	@Override
	public void cancleOrder(long userId, long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ORDER_INFO_NOT_EXIST.getMessage()));

		order.setOrderStatus("cancle");
		orderRepository.save(order);
	}

	@Override
	public FindOrderResponse findOrderByOrderId(long userId, long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ORDER_INFO_NOT_EXIST.getMessage()));

		return FindOrderResponse.of(order);
	}

	@Override
	public FindAllOrderResponse findAllOrderByUserId(long userNo) {

		Consumer consumer = consumerRepository.findById(userNo)
			.orElseThrow(() -> new NullPointerException(ErrorUserCode.ID_NOT_EXIST.getMessage()));

		List<Order> orderList =
			orderRepository.findByConsumerInfo(consumer).stream().filter(Objects::nonNull).collect(Collectors.toList());

		if (orderList.isEmpty()) {
			throw new NullPointerException(ErrorUserCode.ORDER_INFO_NOT_EXIST.getMessage());
		}

		return FindAllOrderResponse.ofList(orderList);

	}

	private int saveOrderList(Order order, RegisterOrderTarget target) {
		int totalPrice = 0;

		List<OrderMenu> orderMenuList = new ArrayList<>();

		for (int i = 0; i < target.getOrderRequestList().size(); i++) {
			List<OrderMenuOption> orderMenuOptionList = new ArrayList<>();

			// 메뉴
			OrderMenu orderMenu = OrderMenu.builder()
				.menuNo(target.getOrderRequestList().get(i).getMenuNo())
				.menuName(target.getOrderRequestList().get(i).getMenuName())
				.menuPrice(target.getOrderRequestList().get(i).getMenuPrice())
				.orderMenuOptionList(orderMenuOptionList)
				.build();

			orderMenu.setOrder(order);
			orderMenuRepository.save(orderMenu);

			// 메뉴옵션
			for (int j = 0; j < target.getOrderRequestList().get(i).getMenuOptionListOfRequest().size(); j++) {
				OrderMenuOption orderMenuOption = OrderMenuOption.builder()
					.menuOptionNo(
						target.getOrderRequestList().get(i).getMenuOptionListOfRequest().get(j).getMenuOptionNo())
					.menuOptionName(
						target.getOrderRequestList().get(i).getMenuOptionListOfRequest().get(j).getMenuOptionName())
					.menuOptionPrice(
						target.getOrderRequestList().get(i).getMenuOptionListOfRequest().get(j).getMenuOptionPrice())
					.build();

				orderMenuOptionList.add(orderMenuOption);
				orderMenuOption.setOrderMenu(orderMenu);
				totalPrice += target.getOrderRequestList()
					.get(i)
					.getMenuOptionListOfRequest()
					.get(j)
					.getMenuOptionPrice();
				orderMenuOptionRepository.save(orderMenuOption);

			}

			orderMenuList.add(orderMenu);
			totalPrice += target.getOrderRequestList().get(i).getMenuPrice();

		}

		// Order 저장장
		order.setOrderMenuList(orderMenuList);
		return totalPrice;

	}
}