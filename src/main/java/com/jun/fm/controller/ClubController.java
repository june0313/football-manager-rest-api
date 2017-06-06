package com.jun.fm.controller;

import com.jun.fm.dto.ClubDto;
import com.jun.fm.controller.exception.ClubCreationFailureException;
import com.jun.fm.controller.exception.ClubNotFoundException;
import com.jun.fm.controller.exception.PlayerAlreadyBelongsToClubException;
import com.jun.fm.controller.exception.PlayerNotFoundException;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.player.Player;
import com.jun.fm.service.ClubService;
import com.jun.fm.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@RestController
@RequestMapping(value = "/clubs")
@Slf4j
public class ClubController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private ClubService clubService;

	@GetMapping("/{id}")
	public ClubDto getById(@PathVariable Long id) {
		return Optional.ofNullable(clubService.findById(id))
			.map(ClubDto::from)
			.orElseThrow(() -> new ClubNotFoundException(id));
	}

	@GetMapping
	public Page<ClubDto> getClubs(Pageable pageable) {
		return clubService.findList(pageable);
	}

	@PostMapping
	public ResponseEntity<ClubDto> create(@RequestBody ClubDto clubDto, UriComponentsBuilder uriComponentsBuilder) {
		String playerName = getCurrentPlayerName();

		Player player = playerService.findByName(playerName);

		if (player == null) {
			throw new PlayerNotFoundException(playerName);
		}

		if (player.belongToClub()) {
			throw new PlayerAlreadyBelongsToClubException(player.getId(), player.getClub().getId());
		}

		Club club = clubService.create(player, clubDto.toEntity());

		if (club == null) {
			throw new ClubCreationFailureException();
		}

		URI location = uriComponentsBuilder.path("/clubs/").path(String.valueOf(club.getId())).build().toUri();

		return ResponseEntity.created(location).body(ClubDto.from(club));
	}

	private String getCurrentPlayerName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
