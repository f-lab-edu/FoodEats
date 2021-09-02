package com.flab.foodeats.application.shop.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.domain.shop.Convenient;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.domain.shop.Status;
import com.flab.foodeats.infra.shop.ShopMapper;

@ExtendWith(MockitoExtension.class)
class ShopServiceImplTest {

	//@InjectMocks
	ShopServiceImpl shopService;

	@Mock
	ShopMapper shopMapper;

	EssentialTarget essentialTarget;
	DeleteEssentialTarget deleteEssentialTarget;
	StatusTarget statusTarget;
	ConvenientTarget convenientTarget;

	@BeforeEach
	void setUp() {
		// @InjectMocks service
		shopService = new ShopServiceImpl(userMapper);
		
		essentialTarget = new EssentialTarget("eunsoo", "noodle", "BBQ", "BUSAN", "01079286788");

		deleteEssentialTarget = new DeleteEssentialTarget("eunsoo");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime closeTime = LocalTime.parse("12:03:02", formatter);
		LocalTime openTime = LocalTime.parse("16:03:02", formatter);
		statusTarget = new StatusTarget(closeTime,openTime,"7");
		convenientTarget = new ConvenientTarget("silver","car","All");
	}

	@Test
	@DisplayName("필수정보등록 성공")
	void registerEssentialShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(null);

		// when
		shopService.registerEssentialShopInfo(essentialTarget);

		//then
		then(shopMapper).should(times(1)).registerEssentialInfo(any());
	}

	@Test
	@DisplayName("필수정보등록 중복")
	void registerEssentialFail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());

		// then
		assertThrows(DuplicateKeyException.class, () -> shopService.registerEssentialShopInfo(essentialTarget));
	}

	@Test
	@DisplayName("필수정보수정 성공")
	void updateEssentialShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());

		// when
		shopService.updateEssentialShopInfo(essentialTarget);

		// then
		then(shopMapper).should(times(1)).updateEssentialInfo(any());
	}

	@Test
	@DisplayName("필수정보수정 실패")
	void updateEssentialFail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> shopService.updateEssentialShopInfo(essentialTarget));
	}

	@Test
	@DisplayName("가맹점 정보 삭제 성공")
	void deleteEssentialShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());

		// when
		shopService.deleteEssentialShopInfo(deleteEssentialTarget);

		// then
		then(shopMapper).should(times(1)).deleteEssentialInfo(any());
		then(shopMapper).should(times(1)).deleteStatusInfo(any());
		then(shopMapper).should(times(1)).deleteConvenienceInfo(any());

	}

	@Test
	@DisplayName("가맹점 정보 삭제 실패")
	void deleteEssentialFail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> shopService.deleteEssentialShopInfo(deleteEssentialTarget));
	}

	@Test
	@DisplayName("상태정보등록 성공")
	void registerStatusShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findStatusByShopId(any())).willReturn(null);

		// when
		shopService.registerStatusShopInfo(statusTarget, essentialTarget.getUserId());

		// then
		then(shopMapper).should(times(1)).registerStatusInfo(any());

	}

	@Test
	@DisplayName("상태정보등록 실패 / 중복문제")
	void registerStatusFail() {
		// given
		given(shopMapper.findStatusByShopId(any())).willReturn(new Status());

		// then
		assertThrows(DuplicateKeyException.class, () -> shopService.registerStatusShopInfo(statusTarget,essentialTarget.getUserId()));
	}

	@Test
	@DisplayName("상태정보등록 실패 / Essential 선행 등록 문제")
	void registerStatusEssetialNullFail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> shopService.registerStatusShopInfo(statusTarget,essentialTarget.getUserId()));
	}

	@Test
	@DisplayName("상태정보수정 성공")
	void updateStatusShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findStatusByShopId(any())).willReturn(new Status());

		// when
		shopService.updateStatusShopInfo(statusTarget, essentialTarget.getUserId());

		// then
		then(shopMapper).should(times(1)).updateStatusInfo(any());
	}

	@Test
	@DisplayName("상태정보수정 실패 / 상태정보 null")
	void updateStatusFail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findStatusByShopId(any())).willReturn(null);

		// then
		assertThrows(NullPointerException.class, () -> shopService.updateStatusShopInfo(statusTarget,essentialTarget.getUserId()));
	}

	@Test
	@DisplayName("편리정보등록 성공")
	void registerConvenienceShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findConvenienceByShopId(any())).willReturn(null);

		// when
		shopService.registerConvenienceShopInfo(convenientTarget, essentialTarget.getUserId());

		// then
		then(shopMapper).should(times(1)).registerConvenienceInfo(any());
	}

	@Test
	@DisplayName("편리정보등록 실패 / 편리정보 중보")
	void registerConveniencefail() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findConvenienceByShopId(any())).willReturn(new Convenient());

		// then
		assertThrows(DuplicateKeyException.class, () -> shopService.registerConvenienceShopInfo(convenientTarget,essentialTarget.getUserId()));
	}

	@Test
	@DisplayName("편리정보수정 성공")
	void updateConvenienceShopInfo() {
		// given
		given(shopMapper.findEssentialByShopId(any())).willReturn(new Essential());
		given(shopMapper.findConvenienceByShopId(any())).willReturn(new Convenient());

		// when
		shopService.updateConvenienceShopInfo(convenientTarget, essentialTarget.getUserId());

		// then
		then(shopMapper).should(times(1)).updateConvenienceInfo(any());
	}

}
