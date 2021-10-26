package com.flab.foodeats.infra.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.menu.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	//List<Menu> findByShopId(long shopId);

	List<Menu> findAllByShopId(long shopId);

}

