package com.jun.fm.listener;

import com.google.common.collect.ImmutableList;
import com.jun.fm.annotation.profile.LocalProfile;
import com.jun.fm.domain.application.Application;
import com.jun.fm.domain.application.ApplicationState;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.game.GameState;
import com.jun.fm.domain.player.Position;
import com.jun.fm.dto.PlayerDto;
import com.jun.fm.repository.ApplicationRepository;
import com.jun.fm.repository.ClubRepository;
import com.jun.fm.repository.GameRepository;
import com.jun.fm.repository.PlayerRepository;
import com.jun.fm.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Component
@LocalProfile
@Transactional
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createPlayersForLocalTest();
		createClubs();
		createGames();
		createApplies();
	}

	private void createPlayersForLocalTest() {
		ImmutableList.of(
			PlayerDto.of("player1", "player1@test.com", "password", Position.DEFENDER),
			PlayerDto.of("player2", "player2@test.com", "password", Position.FORWARD),
			PlayerDto.of("player3", "player3@test.com", "password", Position.MIDFIELDER),
			PlayerDto.of("player4", "player4@test.com", "password", Position.GOALKEEPER),
			PlayerDto.of("player5", "player5@test.com", "password", Position.MIDFIELDER),
			PlayerDto.of("player6", "player6@test.com", "password", Position.MIDFIELDER)
		).forEach(playerService::create);
	}

	private void createClubs() {
		Club clubReal = Club.create().setName("real");
		playerRepository.findOne(1L).setClub(clubReal);
		playerRepository.findOne(2L).setClub(clubReal);

		Club clubArsenal = Club.create().setName("arsenal");
		playerRepository.findOne(3L).setClub(clubArsenal);
		playerRepository.findOne(4L).setClub(clubArsenal);

		Club clubManu = Club.create().setName("manu");
		playerRepository.findOne(5L).setClub(clubManu);
		playerRepository.findOne(6L).setClub(clubManu);

		clubRepository.save(Arrays.asList(clubReal, clubArsenal, clubManu));
	}

	private void createGames() {
		Club club = clubRepository.findOne(1L);

		Game game = new Game();
		game.setHost(club);
		game.setState(GameState.OPENED);
		game.setDetails("게임해요");
		game.setMatchLocation("관악구 축구장");
		game.setMatchDate(LocalDateTime.now().plusDays(7));

		gameRepository.save(game);
	}

	private void createApplies() {
		Club club = clubRepository.findOne(2L);
		Game game = gameRepository.findOne(1L);

		Application application = new Application();
		application.setClub(club);
		application.setGame(game);
		application.setMessage("저희 팀이랑 한판 해요");
		application.setState(ApplicationState.PENDING);

		Club club2 = clubRepository.findOne(3L);

		Application application2 = new Application();
		application2.setClub(club2);
		application2.setGame(game);
		application2.setMessage("게임을 신청합니다.");
		application2.setState(ApplicationState.PENDING);

		applicationRepository.save(Arrays.asList(application, application2));
	}

}
