package com.flab.foodeats.api.order;

import java.util.List;

import com.flab.foodeats.domain.order.OrderMenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuListOfResponse {


	private Long orderNo;

	private long shopNo;
	private String shopBrand;
	private String shopCategory;

	private List<OrderMenu> orderMenu;
	private int totalPrice;
	private String orderStatus;

}
