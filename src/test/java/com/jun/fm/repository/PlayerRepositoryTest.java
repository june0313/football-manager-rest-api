package com.jun.fm.repository;

import com.jun.fm.annotation.profile.ActiveTestProfile;
import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.player.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveTestProfile
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository repository;

	@Test
	public void saveAndFindAll() throws Exception {
		// given
		Player newPlayer = newPlayer();

		// when
		repository.save(newPlayer);

		// then
		List<Player> players = repository.findAll();
		assertThat(players).hasSize(1);
		assertThat(players.get(0).getName()).isEqualTo("test");
		assertThat(players.get(0).getEmail()).isEqualTo("email@email.com");
		assertThat(players.get(0).getPassword()).isEqualTo("pw");
		assertThat(players.get(0).getPosition()).isEqualTo(Position.FORWARD);
	}

	@Test
	public void update() throws Exception {
		// given
		repository.save(newPlayer());

		// when
		Player foundPlayer = repository.findOne(1L);
		foundPlayer.setPosition(Position.GOALKEEPER);
		repository.save(foundPlayer);

		// then
		Player resultPlayer = repository.findOne(1L);
		assertThat(resultPlayer.getPosition()).isEqualTo(Position.GOALKEEPER);
	}

	@Test
	public void delete() throws Exception {
		// given
		repository.save(newPlayer());

		// when
		repository.delete(1L);

		// then
		List<Player> players = repository.findAll();
		assertThat(players).isEmpty();
	}

	private Player newPlayer() {
		return Player.create().setName("test").setEmail("email@email.com").setPassword("pw").setPosition(Position.FORWARD);
	}
}