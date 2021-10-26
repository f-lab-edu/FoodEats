package com.flab.foodeats.application.menu;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionTarget {

	private long menuOptionId;
	private long menuId;
	private String menuOptionName;
	private int menuOptionPrice;

	public OptionTarget() {
	}

	@Builder
	public OptionTarget(long menuOptionId, long menuId, String menuOptionName, int menuOptionPrice) {
		this.menuOptionId = menuOptionId;
		this.menuId = menuId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}
}
