package com.flab.foodeats.application.shop.adapter;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.RegisterEssentialTarget;
import com.flab.foodeats.application.shop.port.ShopService;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.infra.shop.ShopMapper;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;
	//private ShopErrorCheck shopErrorCheck;
	//private ShopAutomaticStatus shopAutomaticStatus;

	public ShopServiceImpl(ShopMapper shopMapper) {
		this.shopMapper = shopMapper;
	}

	// 가맹점 등록 (기본정보)
	public void registerEssentialShopInfo(RegisterEssentialTarget target) {

		Essential essential = shopMapper.findEssentialByShopId(target.getShopId());
		shopInfoAlreadyExist(essential);
		shopMapper.registerEssentialInfo(target.toEntity());

	}


	public <T> void shopInfoAlreadyExist(T entity) {
		if (entity != null) {
			throw new DuplicateKeyException(ErrorUserCode.SHOP_EXIST.getMessage());
		}
	}

	public void shopEssentialInfoNotExist(Essential essential) {
		if (essential == null) {
			throw new DuplicateKeyException(ErrorUserCode.ESSENTIAL_INFO_NOT_EXIST.getMessage());
		}
	}
}
