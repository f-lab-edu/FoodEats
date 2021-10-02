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


	void saveConsumer(@Param("Consumer") User user);
	void saveMerchant(@Param("Merchant") User user);
	void saveRider(@Param("Rider") User user);

	User findConsumerByUserId(String userId);
	User findMerchantByUserId(String userId);
	User findRiderByUserId(String userId);

	User findConsumerById(long id);
	User findMerchantByShopId(long shopId);
	User findRiderByRiderId(long riderId);

	void modifyConsumerById( @Param("Consumer") User user);
	void modifyMerchantById( @Param("Merchant") User user);
	void modifyRiderById( @Param("Rider") User user);

	void deleteConsumerById(String userId);
	void deleteMerchantById(String userId);
	void deleteRiderById(String userId);


}
