package com.flab.foodeats.shop.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.flab.foodeats.shop.search.model.SearchShopResultDTO;

@Mapper
public interface ShopSearchMapper {

	List<SearchShopResultDTO> findShopByCategory(String category);

	List<SearchShopResultDTO> findShopByBrand(String brand);
}
