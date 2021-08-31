package com.flab.foodeats.application.menu.port;

import java.util.List;

import com.flab.foodeats.api.menu.OptionRequest;

public interface MenuOptionService {

	void registerMenuOption(int shopId, int menuId, List<OptionRequest> optionRequests, String userId);

	void updateMenuOption(int shopId, int menuOptionId, List<OptionRequest> optionRequests, String userId);

	void deleteMenuOption(int shopId, int menuOptionId, String userId);
}
