package com.jun.fm.controller;

import com.jun.fm.controller.dto.ClubCreationDto;
import com.jun.fm.controller.dto.ClubDto;
import com.jun.fm.controller.exception.ClubCreationFailureException;
import com.jun.fm.controller.exception.ClubNotFoundException;
import com.jun.fm.controller.exception.Error;
import com.jun.fm.domain.club.Club;
import com.jun.fm.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private ClubService clubService;

	@GetMapping("/{id}")
	public ClubDto getById(@PathVariable Long id) {
		return Optional.ofNullable(clubService.findById(id))
			.map(ClubDto::from)
			.orElseThrow(() -> new ClubNotFoundException(id));
	}

	@PostMapping
	public ResponseEntity<ClubDto> createClub(@RequestBody ClubCreationDto clubCreationDto, UriComponentsBuilder uriComponentsBuilder) {
		// FIXME : 클럽 생성에 실패하는 이유를 세분화 하기(선수 없음, 해당 선수의 클럽이 이미 존재)
		Club club = clubService.createClub(clubCreationDto.getOwnerId(), clubCreationDto.getClub());

		if (club == null) {
			throw new ClubCreationFailureException();
		}

		ClubDto clubDto = ClubDto.from(club);
		URI location = uriComponentsBuilder.path("/clubs/").path(String.valueOf(club.getId())).build().toUri();

		return ResponseEntity.created(location).body(clubDto);
	}

	@ExceptionHandler(ClubCreationFailureException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error clubCreationFail(ClubCreationFailureException e) {
		return Error.of(HttpStatus.CONFLICT.value(), "Fail to create club");
	}

	@ExceptionHandler(ClubNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error clubNotFound(ClubNotFoundException e) {
		return Error.of(HttpStatus.NOT_FOUND.value(), String.format("Club %d not found", e.getClubId()));
	}

}
