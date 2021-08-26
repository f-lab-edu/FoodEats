package com.flab.foodeats.api.menu;

import java.util.List;

public class OptionRequest {

	private List<MenuOption> menuOptions;

	public OptionRequest() {
	}

	public OptionRequest(List<MenuOption> menuOptions) {
		this.menuOptions = menuOptions;
	}

	public List<MenuOption> getMenuOptions() {
		return menuOptions;
	}

	public OptionTarget toParam() {
		return new OptionTarget(menuOptions);
	}
}
