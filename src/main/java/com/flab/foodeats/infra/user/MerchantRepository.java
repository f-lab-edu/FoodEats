package com.flab.foodeats.infra.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.user.Merchant;
import com.flab.foodeats.domain.user.User;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	Optional<Merchant> findByUserId(String userId);
	boolean existsByUserId(String userId);
}
