package com.flab.foodeats.application.shop.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.application.shop.autostatus.ShopAutomaticStatus;
import com.flab.foodeats.application.shop.holiday.PublicHoliday;
import com.flab.foodeats.application.shop.holiday.Weekend;
import com.flab.foodeats.application.shop.port.ShopService;

import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.domain.shop.Status;
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
	public void registerEssentialShopInfo(AuthInfo authInfo, EssentialTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.toEntity().getShopId());
		shopInfoAlreadyExist(essential);
		shopMapper.registerEssentialInfo(target.toEntity());
	}

	// 가맹점 수정 (기본정보)
	public void updateEssentialShopInfo(AuthInfo authInfo, EssentialTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential getEssentialInfo = shopEssentialInfo(target.toEntity().getShopId());
		shopEntityInfoNotExist(getEssentialInfo);
		Essential changeEssentialInfo = target.toEntity();
		shopMapper.updateEssentialInfo(changeEssentialInfo);
	}

	// 가맹점 삭제 (기본정보)
	public void deleteEssentialShopInfo(AuthInfo authInfo, DeleteEssentialTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.getShopId());
		shopEntityInfoNotExist(essential);
		shopMapper.deleteEssentialInfo(essential.getShopId());
		shopMapper.deleteStatusInfo(essential.getShopId());
		shopMapper.deleteConvenienceInfo(essential.getShopId());
	}

	// 가맹점 등록 (상태정보)
	public void registerStatusShopInfo(AuthInfo authInfo, StatusTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.toEntity().getShopId());
		shopInfoAlreadyExist(shopMapper.findStatusByShopId(target.toEntity().getShopId()));
		shopEessentialInfoNotExist(essential);
		shopMapper.registerStatusInfo(target.toEntity());
	}

	// 가맹점 수정 (상태정보)
	public void updateStatusShopInfo(AuthInfo authInfo, StatusTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.toEntity().getShopId());
		shopEntityInfoNotExist(shopMapper.findStatusByShopId(target.toEntity().getShopId()));
		shopMapper.updateStatusInfo(target.toEntity());
	}

	// 가맹점 등록 (편리정보)
	public void registerConvenienceShopInfo(AuthInfo authInfo, ConvenientTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.toEntity().getShopId());
		shopInfoAlreadyExist(shopMapper.findConvenienceByShopId(target.toEntity().getShopId()));
		shopEessentialInfoNotExist(essential);
		shopMapper.registerConvenienceInfo(target.toEntity());
	}

	// 가맹점 수정 (편리정보)
	public void updateConvenienceShopInfo(AuthInfo authInfo, ConvenientTarget target) {
		validShopOwner(authInfo.getUserId(), target.getShopId());
		Essential essential = shopEssentialInfo(target.toEntity().getShopId());
		shopEntityInfoNotExist(shopMapper.findConvenienceByShopId(target.toEntity().getShopId()));
		shopMapper.updateConvenienceInfo(target.toEntity());
	}

	/**
	 * 추후 삭제
	 * 동작을 확인하기 위한 조회입니다.
	 * 추후 삭제할 부분 입니다!
	 */

	public List<Essential> searchShopAllInfo(StatusTarget target) {

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

		/**
		 * todo
		 * null 체크 / 데이터 있는지
		 */
		// 가맹점 자동상태 변환
		Status status = Optional.of(shopMapper.findStatusByShopId(target.toEntity().getShopId()))
			.orElse(null);

		if (new ShopAutomaticStatus().changeShopStatusAuto(status)) {
			shopMapper.startShop(status);
		} else {
			shopMapper.closeShop(status);
		}

		return shopMapper.shopListAllInfo();
	}

	private Essential shopEssentialInfo(Long shopId) {
		return shopMapper.findEssentialByShopId(shopId);
	}

	private void validShopOwner(String authedUserId, Long requestedShopId){
		String requestedUserId = userMapper.findMerchantByShopId(requestedShopId).getUserId();
		if(!authedUserId.equals(requestedUserId)){
			throw new SecurityException(ErrorUserCode.ID_NOT_MATCH.getMessage());
		}
	}

	private <T> void shopInfoAlreadyExist(T entity) {
		if (entity != null) {
			throw new DuplicateKeyException(ErrorUserCode.SHOP_EXIST.getMessage());
		}
	}

	private <T> void shopEntityInfoNotExist(T entity) {
		if (entity == null) {
			throw new NullPointerException(ErrorUserCode.ENTITY_INFO_NOT_EXIST.getMessage());
		}
	}

	private void shopEessentialInfoNotExist(Essential essential) {
		if (essential == null) {
			throw new NullPointerException(ErrorUserCode.ESSENTIAL_INFO_NOT_EXIST.getMessage());
		}
	}
}
