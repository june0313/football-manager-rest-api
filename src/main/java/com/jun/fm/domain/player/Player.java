package com.jun.fm.domain.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.role.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Entity
@Data(staticConstructor = "create")
@Accessors(chain = true)
public class Player {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	@JsonIgnore
	private String password;

	@Column(unique = true)
	private String email;

	@Enumerated(value = EnumType.STRING)
	private Position position;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	@ManyToOne
	private Club club;

	public boolean belongToClub() {
		return club != null;
	}

}
