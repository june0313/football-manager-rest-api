package com.jun.fm.domain.club;

import com.google.common.collect.Lists;
import com.jun.fm.domain.application.Application;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
public class Club {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne
	private Player owner;

	@OneToMany(mappedBy = "club")
	private List<Player> players;

	@OneToMany(mappedBy = "host")
	private List<Game> games;

	@OneToMany(mappedBy = "club")
	private List<Application> applications;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	@PrePersist
	private void prePersist() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

	public void addPlayer(Player player) {
		if (this.players == null) {
			this.players = Lists.newArrayList();
		}

		this.players.add(player);
	}

	public void addGame(Game game) {
		if (this.games == null) {
			this.games = Lists.newArrayList();
		}

		this.games.add(game);
	}

	public void addApplication(Application application) {
		if (this.applications == null) {
			this.applications = Lists.newArrayList();
		}

		this.applications.add(application);
	}

}
