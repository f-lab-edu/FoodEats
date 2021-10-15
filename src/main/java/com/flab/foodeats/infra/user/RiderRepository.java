package com.flab.foodeats.infra.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.foodeats.domain.user.Rider;
import com.flab.foodeats.domain.user.User;

public interface RiderRepository extends JpaRepository<Rider, Long> {
	Optional<Rider> findByUserId(String userId);
	boolean existsByUserId(String userId);
}
