package com.jun.fm.service;

import com.jun.fm.dto.ClubDto;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.player.Player;
import com.jun.fm.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;

	public Club findById(Long id) {
		return clubRepository.findOne(id);
	}

	public Page<ClubDto> findList(Pageable pageable) {
		return clubRepository.findAll(pageable).map(ClubDto::from);
	}

	public Club create(Player owner, Club club) {
		owner.setClub(club);
		return clubRepository.save(club);
	}

}
