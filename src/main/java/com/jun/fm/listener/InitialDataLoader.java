package com.jun.fm.listener;

import com.jun.fm.annotation.profile.LocalProfile;
import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.player.Position;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Component
@LocalProfile
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		playerRepository.save(Arrays.asList(
			new Player().setName("testPlayer1").setEmail("test1@test.com").setPassword("password").setPosition(Position.DEFENDER),
			new Player().setName("testPlayer2").setEmail("test2@test.com").setPassword("password").setPosition(Position.FORWARD),
			new Player().setName("testPlayer3").setEmail("test3@test.com").setPassword("password").setPosition(Position.GOALKEEPER),
			new Player().setName("testPlayer4").setEmail("test4@test.com").setPassword("password").setPosition(Position.MIDFIELDER)
		));
	}

}
