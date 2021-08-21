package com.flab.foodeats.infra.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.application.user.ModifyUserTarget;
import com.flab.foodeats.application.user.RegisterUserTarget;
import com.flab.foodeats.domain.user.User;

@Mapper
@Repository
public interface UserMapper {


	void registerConsumer(@Param("Consumer") RegisterUserTarget registerUserTarget);
	void registerMerchant(@Param("Merchant") RegisterUserTarget registerUserTarget);
	void registerRider(@Param("Rider") RegisterUserTarget registerUserTarget);

	User findConsumerInfoById(String userId);
	User findMerchantInfoById(String userId);
	User findRiderInfoById(String userId);

	void modifyConsumerInfoById( @Param("Consumer") ModifyUserTarget modifyUserTarget);
	void modifyMerchantInfoById( @Param("Merchant") ModifyUserTarget modifyUserTarget);
	void modifyRiderInfoById( @Param("Rider") ModifyUserTarget modifyUserTarget);

}