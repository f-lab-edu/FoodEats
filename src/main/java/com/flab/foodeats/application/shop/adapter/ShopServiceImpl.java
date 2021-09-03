package com.flab.foodeats.application.shop.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.DeleteShopInfoTarget;
import com.flab.foodeats.application.shop.ShopDeliveryTarget;
import com.flab.foodeats.application.shop.ShopInfoTarget;
import com.flab.foodeats.application.shop.BusinessHourTarget;
import com.flab.foodeats.application.shop.autostatus.ShopAutomaticStatus;
import com.flab.foodeats.application.shop.holiday.PublicHoliday;
import com.flab.foodeats.application.shop.holiday.Weekend;
import com.flab.foodeats.application.shop.port.ShopService;

import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.shop.BusinessHour;
import com.flab.foodeats.infra.shop.ShopMapper;
import com.flab.foodeats.infra.user.UserMapper;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;
	private UserMapper userMapper;


	public ShopServiceImpl(ShopMapper shopMapper, UserMapper userMapper) {
		this.shopMapper = shopMapper;
		this.userMapper = userMapper;
	}


	// 가맹점 등록 (기본정보)
	public void registerShopInfo(AuthInfo authInfo, ShopInfoTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.toEntity().getShopId());
		infoAlreadyExist(shop);
		shopMapper.registerShopInfo(target.toEntity());
	}

	// 가맹점 수정 (기본정보)
	public void updateShopInfo(AuthInfo authInfo, ShopInfoTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop getShopInfo = findShopInfo(target.toEntity().getShopId());
		infoNotExist(getShopInfo);
		Shop changeShopInfo = target.toEntity();
		shopMapper.updateShopInfo(changeShopInfo);
	}

	// 가맹점 삭제 (기본정보)
	public void deleteShopInfo(AuthInfo authInfo, DeleteShopInfoTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.getShopId());
		infoNotExist(shop);
		shopMapper.deleteShopInfo(shop.getShopId());
		shopMapper.deleteBusinessHour(shop.getShopId());
		shopMapper.deleteShopDeliveryInfo(shop.getShopId());
	}

	// 가맹점 등록 (상태정보)
	public void registerBusinessHour(AuthInfo authInfo, BusinessHourTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.toEntity().getShopId());
		shopInfoNotExist(shop);
		infoAlreadyExist(shopMapper.findBusinessHourByShopId(target.toEntity().getShopId()));
		shopMapper.registerBusinessHour(target.toEntity());
	}

	// 가맹점 수정 (상태정보)
	public void updateBusinessHour(AuthInfo authInfo, BusinessHourTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.toEntity().getShopId());
		shopInfoNotExist(shop);
		infoNotExist(shopMapper.findBusinessHourByShopId(target.toEntity().getShopId()));
		shopMapper.updateBusinessHour(target.toEntity());
	}

	// 가맹점 등록 (편리정보)
	public void registerShopDeliveryInfo(AuthInfo authInfo, ShopDeliveryTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.toEntity().getShopId());
		shopInfoNotExist(shop);
		infoAlreadyExist(shopMapper.findShopDeliveryInfoByShopId(target.toEntity().getShopId()));
		shopMapper.registerShopDeliveryInfo(target.toEntity());
	}

	// 가맹점 수정 (편리정보)
	public void updateShopDeliveryInfo(AuthInfo authInfo, ShopDeliveryTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Shop shop = findShopInfo(target.toEntity().getShopId());
		shopInfoNotExist(shop);
		infoNotExist(shopMapper.findShopDeliveryInfoByShopId(target.toEntity().getShopId()));
		shopMapper.updateShopDeliveryInfo(target.toEntity());
	}

	/**
	 * 추후 삭제
	 * 동작을 확인하기 위한 조회입니다.
	 * 추후 삭제할 부분 입니다!
	 */

	public List<Shop> searchShopAllInfo(BusinessHourTarget target) {

		// 주말 체크
		if (new Weekend().weekendCheck()) {
			System.out.println("주말");
		} else {
			System.out.println("주말x");
		}

		// 공휴일 체크
		if (new PublicHoliday().calculator()) {
			System.out.println("공휴일");
		} else {
			System.out.println("공휴일x");
		}

		// 가맹점 자동상태 변환
		BusinessHour businessHour = Optional.of(shopMapper.findBusinessHourByShopId(target.toEntity().getShopId()))
			.orElse(null);

		if (new ShopAutomaticStatus().changeShopStatusAuto(businessHour)) {
			shopMapper.startShop(businessHour);
		} else {
			shopMapper.closeShop(businessHour);
		}

		return shopMapper.shopListAllInfo();
	}

	private Shop findShopInfo(Long shopId) {
		return shopMapper.findShopInfoByShopId(shopId);
	}


	private void validShopOwner(String authedUserId, Long requestedShopId){
		String requestedUserId = userMapper.findMerchantByShopId(requestedShopId).getUserId();
		if(!authedUserId.equals(requestedUserId)){
			throw new SecurityException(ErrorUserCode.ID_NOT_MATCH.getMessage());
		}
	}
	private <T> void infoAlreadyExist(T entity) {
		if (entity != null) {
			throw new DuplicateKeyException(ErrorUserCode.SHOP_EXIST.getMessage());
		}
	}

	private <T> void infoNotExist(T entity) {
		if (entity == null) {
			throw new NullPointerException(ErrorUserCode.ENTITY_INFO_NOT_EXIST.getMessage());
		}
	}

	private void shopInfoNotExist(Shop shop) {
		if (shop == null) {
			throw new NullPointerException(ErrorUserCode.SHOP_INFO_NOT_EXIST.getMessage());
		}
	}
}
