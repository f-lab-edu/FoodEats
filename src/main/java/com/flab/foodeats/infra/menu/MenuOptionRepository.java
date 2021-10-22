package com.flab.foodeats.infra.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.menu.MenuOption;

@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {

	// @Transactional
	// @Query("SELECT u from MenuOption u where u.menuId = :menuId")
	List<MenuOption> findAllByMenuId(long menuId);
}

