package com.flab.foodeats.application.menu;

import java.util.List;

public class OptionTarget {

	private List<MenuOption> menuOptions;

	public OptionTarget() {
	}

	public OptionTarget(List<MenuOption> menuOptions) {
		this.menuOptions = menuOptions;
	}

	public List<MenuOption> getMenuOptions() {
		return menuOptions;
	}
}
