package com.jun.fm.domain.game;

import com.google.common.collect.Lists;
import com.jun.fm.domain.BaseEntity;
import com.jun.fm.domain.application.Application;
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
public class Game extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	private Club host;

	@OneToMany(mappedBy = "game")
	private List<Application> applications;

	private LocalDateTime matchDate;

	private String matchLocation;

	private String details;

	@Enumerated(EnumType.STRING)
	private GameState state = GameState.OPENED;

	public void setHost(Club club) {
		this.host = club;
		club.addGame(this);
	}

	public void addApplication(Application application) {
		if (this.applications == null) {
			this.applications = Lists.newArrayList();
		}

		this.applications.add(application);
	}

}
