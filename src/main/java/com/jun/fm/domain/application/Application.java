package com.jun.fm.domain.application;

import com.jun.fm.domain.BaseEntity;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Entity
@Getter
@Setter
public class Application extends BaseEntity{

	@ManyToOne
	private Game game;

	@ManyToOne
	private Club club;

	private String message;

	@Enumerated(EnumType.STRING)
	private ApplicationState state;

	public void setClub(Club club) {
		this.club = club;
		club.addApplication(this);
	}

	public void setGame(Game game) {
		this.game = game;
		game.addApplication(this);
	}

}
