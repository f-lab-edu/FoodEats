package com.flab.foodeats.application.order;

import java.util.List;

import com.flab.foodeats.api.order.RegisterOrderRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RegisterOrderTarget {

	private long userNo;
	private long shopNo;
	private List<RegisterOrderRequest> orderRequestList;


}
