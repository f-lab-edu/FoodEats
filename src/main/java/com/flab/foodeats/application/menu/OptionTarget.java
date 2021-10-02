package com.flab.foodeats.application.menu;

public class OptionTarget {

	private long menuOptionId;
	private String menuOptionName;
	private int menuOptionPrice;

	public OptionTarget() {
	}

	public OptionTarget(long menuOptionId, String menuOptionName, int menuOptionPrice) {
		this.menuOptionId = menuOptionId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public long getMenuOptionId() {
		return menuOptionId;
	}

	public String getMenuOptionName() {
		return menuOptionName;
	}

	public int getMenuOptionPrice() {
		return menuOptionPrice;
	}
}
