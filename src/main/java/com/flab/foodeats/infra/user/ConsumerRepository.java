package com.flab.foodeats.infra.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.user.Consumer;
import com.flab.foodeats.domain.user.User;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
	Optional<Consumer> findByUserId(String userId);
	boolean existsByUserId(String userId);
}
