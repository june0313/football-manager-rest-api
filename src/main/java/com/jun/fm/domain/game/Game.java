package com.jun.fm.domain.game;

import com.jun.fm.domain.club.Club;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 6. 3..
 *
 */
@Entity
@Data
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Club host;

	private LocalDateTime matchDate;

	private String matchLocation;

	private String details;

	@Enumerated(EnumType.STRING)
	private GameState state = GameState.OPEN;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	public void setHost(Club club) {
		this.host = club;
		club.addGames(this);
	}

	@PrePersist
	private void prePersist() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

}
