package com.jun.fm.repository;

import com.jun.fm.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
	Player findPlayerByName(String name);
}
