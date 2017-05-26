package com.jun.fm.service;

import com.jun.fm.domain.player.Player;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public Player findById(Long id) {
		return playerRepository.findOne(id);
	}

}
