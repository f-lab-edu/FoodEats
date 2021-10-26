package com.flab.foodeats.api.order.adapter;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.order.FindAllOrderResponse;
import com.flab.foodeats.api.order.FindOrderResponse;
import com.flab.foodeats.api.order.RegisterOrderRequest;
import com.flab.foodeats.application.order.port.OrderService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.user.UserType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApi {

	private final OrderService orderService;

	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/{user-Id}/shop/{shop-Id}")
	public ResponseEntity<?> registerMenu(@PathVariable("user-Id") long userId, @PathVariable("shop-Id") long shopId,
		@Valid @RequestBody List<RegisterOrderRequest> dto, @AuthUsed AuthInfo authInfo) {
		System.out.println(dto);

		RegisterOrderRequest registerOrderRequest = new RegisterOrderRequest();
		FindOrderResponse response = orderService.registerOrder(registerOrderRequest.toParam(userId, shopId, dto));

		ApiResponse apiResponse = ApiResponse.responseData(StatusCode.SUCCESS,
			SuccessUserCode.TAKE_ORDER_SUCCESS.getMessage(),response);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/{user-id}/shop/{order-id}")
	public ResponseEntity<?> cancleOrder(@PathVariable("user-id") long userId, @PathVariable("order-id") long orderId,
		@Valid @RequestBody List<RegisterOrderRequest> dto, @AuthUsed AuthInfo authInfo) {

		orderService.cancleOrder(userId, orderId);

		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
			SuccessUserCode.CANCLE_ORDER_SUCCESS.getMessage());

		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@AuthRequired(role = UserType.MERCHANT)
	@GetMapping("/{user-id}/{order-id}")
	public ResponseEntity<?> findOrderByOrderId(@PathVariable("user-id") long userId, @PathVariable("order-id") long orderId,
		    @AuthUsed AuthInfo authInfo) {

		FindOrderResponse response = orderService.findOrderByOrderId(userId, orderId);

		ApiResponse apiResponse = ApiResponse.responseData(StatusCode.SUCCESS,
			SuccessUserCode.FIND_ORDER_INFO_SUCCESS.getMessage(),response);

		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@AuthRequired(role = UserType.MERCHANT)
	@GetMapping("/{user-id}/all")
	public ResponseEntity<?> findAllOrderByUserId(@PathVariable("user-id") long userId,
		@AuthUsed AuthInfo authInfo) {

		FindAllOrderResponse responseList = orderService.findAllOrderByUserId(userId);

		ApiResponse apiResponse = ApiResponse.responseData(StatusCode.SUCCESS,
			SuccessUserCode.FIND_ORDER_ALL_INFO_SUCCESS.getMessage(),responseList);

		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
}
