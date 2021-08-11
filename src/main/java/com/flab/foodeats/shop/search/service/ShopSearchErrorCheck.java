package com.flab.foodeats.shop.search.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flab.foodeats.shop.search.model.SearchShopResultDTO;
import com.flab.foodeats.shop.search.model.code.SearchErrorCode;

@Component
public class ShopSearchErrorCheck {
	public void resultEmptyCheck (List<SearchShopResultDTO> result) {
		if (result.isEmpty()) {
			throw new NullPointerException(SearchErrorCode.NO_SEARCH_RESULT.getMessage());
		}
	}
}
