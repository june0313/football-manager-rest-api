package com.jun.fm.repository;

import com.jun.fm.domain.application.Application;
import com.jun.fm.domain.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Page<Application> findAllByGame(Game game, Pageable pageable);
}
