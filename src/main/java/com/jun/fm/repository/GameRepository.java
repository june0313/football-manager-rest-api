package com.jun.fm.repository;

import com.jun.fm.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2017. 6. 3..
 *
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
