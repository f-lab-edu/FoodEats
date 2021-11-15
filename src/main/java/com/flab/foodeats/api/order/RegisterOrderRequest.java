package com.flab.foodeats.api.order;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.order.RegisterOrderTarget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOrderRequest {

	@NotBlank(message = "Input menuNo ")
	private long menuNo;
	@NotBlank(message = "Input menuName ")
	private String menuName;
	@NotBlank(message = "Input menuPrice ")
	private int menuPrice;

	private List<MenuOptionListOfRequest> menuOptionListOfRequest;


	public RegisterOrderTarget toParam(long userNo, long shopNo, List<RegisterOrderRequest> dto) {
		return RegisterOrderTarget.builder()
			.userNo(userNo)
			.shopNo(shopNo)
			.orderRequestList(dto)
			.build();
	}

}
