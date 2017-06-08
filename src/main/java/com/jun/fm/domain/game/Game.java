package com.jun.fm.domain.game;

import com.google.common.collect.Lists;
import com.jun.fm.domain.apply.Apply;
import com.jun.fm.domain.club.Club;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

	@OneToMany(mappedBy = "game")
	private List<Apply> applies;

	private LocalDateTime matchDate;

	private String matchLocation;

	private String details;

	@Enumerated(EnumType.STRING)
	private GameState state = GameState.OPEN;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	public void setHost(Club club) {
		this.host = club;
		club.addGame(this);
	}

	public void addApply(Apply apply) {
		if (this.applies == null) {
			this.applies = Lists.newArrayList();
		}

		this.applies.add(apply);
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
