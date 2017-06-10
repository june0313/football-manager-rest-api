package com.jun.fm.controller;

import com.jun.fm.domain.application.Application;
import com.jun.fm.dto.ApplicationDto;
import com.jun.fm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("/games/{gameId}/applications")
	public List<ApplicationDto> getGameApplications(@PathVariable Long gameId, Pageable pageable) {
		List<Application> applications = applicationService.findByGameId(gameId, pageable);
		return applications.stream().map(ApplicationDto::from).collect(Collectors.toList());
	}

	@PutMapping("/games/{gameId}/applications/{applicationId}")
	public void acceptApplication(@PathVariable Long gameId, @PathVariable Long applicationId) {
		//FIXME : Game을 주최한 Club 만 신청을 수락할 수 있도록 해야한다.
		applicationService.accept(gameId, applicationId);
	}

}
