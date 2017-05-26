package com.jun.fm.domain.player;

import com.jun.fm.domain.club.Club;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Entity
@Data
@Accessors(chain = true)
public class Player {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String email;

	private String password;

	@Enumerated(value = EnumType.STRING)
	private Position position;

	@ManyToOne
	private Club club;

}
