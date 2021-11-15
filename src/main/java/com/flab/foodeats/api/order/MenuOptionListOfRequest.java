package com.flab.foodeats.api.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MenuOptionListOfRequest {
	private long menuOptionNo;
	private String menuOptionName;
	private int menuOptionPrice;

}
