package com.jun.fm.service;

import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.player.Player;
import com.jun.fm.repository.ClubRepository;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayerRepository playerRepository;

	public Club findById(Long id) {
		return clubRepository.findOne(id);
	}

	public Club createClub(Long ownerId, Club club) {
		Player owner = playerRepository.findOne(ownerId);

		if (owner == null || owner.belongToClub()) {
			return null;
		}

		owner.setClub(club);
		club.setOwner(owner);
		club.addPlayer(owner);

		return clubRepository.save(club);
	}

}
