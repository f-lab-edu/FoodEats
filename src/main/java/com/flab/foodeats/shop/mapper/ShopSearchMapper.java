package com.flab.foodeats.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;

@Mapper
public interface ShopSearchMapper {

	List<ShopRegistrationDTO> findAllShop();

	List<ShopRegistrationDTO> findShopById(int shopId);

	List<ShopRegistrationDTO> findShopByCategory(String category);

	List<ShopRegistrationDTO> findShopByBrand(String brand);

	List<Integer> findShopIsOpen();

	List<ShopRegistrationDTO> findShopByLocation(String location);
}
