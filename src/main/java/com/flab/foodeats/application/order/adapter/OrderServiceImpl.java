package com.flab.foodeats.application.order.adapter;

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
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.Merchant;
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

		if(orderList.isEmpty()){
			throw new NullPointerException(ErrorUserCode.ORDER_INFO_NOT_EXIST.getMessage());
		}

		return FindAllOrderResponse.ofList(orderList);

	}



	private int saveOrderList(Order order, RegisterOrderTarget target){
		int totalPrice = 0;
		for (int i = 0; i < target.getOrderRequestList().size(); i++) {

			if (i == 0) {
				OrderMenu orderMenu = new OrderMenu();
				totalPrice = saveMenuOptionList(target, i, totalPrice, orderMenu);
				order.changeOrderMenu(orderMenu);
			}
			if (i == 1) {
				OrderMenu orderMenu = new OrderMenu();
				totalPrice = saveMenuOptionList(target, i, totalPrice, orderMenu);
				order.changeOrderMenu1(orderMenu);
			}

		}
		return totalPrice;

	}

	private int saveMenuOptionList(RegisterOrderTarget target, int i, int totalPrice , OrderMenu orderMenu){


		orderMenu.setMenuNo(target.getOrderRequestList().get(i).getMenuNo());
		orderMenu.setMenuName(target.getOrderRequestList().get(i).getMenuName());
		orderMenu.setMenuPrice(target.getOrderRequestList().get(i).getMenuPrice());

		List<MenuOptionListOfRequest> menuOptionList  = target.getOrderRequestList().get(i).getMenuOptionListOfRequest();
		int totalOptionPrice = 0;

		for (int index =0 ; index < menuOptionList.size() ; index++ ){

			if(index == 0){

				orderMenu.setMenuOptionNo1(menuOptionList.get(index).getMenuOptionNo());
				orderMenu.setMenuOptionName1(menuOptionList.get(index).getMenuOptionName());
				orderMenu.setMenuOptionPrice1(menuOptionList.get(index).getMenuOptionPrice());
			}
			if(index == 1){
				orderMenu.setMenuOptionNo2(menuOptionList.get(index).getMenuOptionNo());
				orderMenu.setMenuOptionName2(menuOptionList.get(index).getMenuOptionName());
				orderMenu.setMenuOptionPrice2(menuOptionList.get(index).getMenuOptionPrice());
			}


			totalOptionPrice += menuOptionList.get(index).getMenuOptionPrice();
		}

		totalPrice += (target.getOrderRequestList().get(i).getMenuPrice()
			+ totalOptionPrice);


		return totalPrice;
	}
}
