package com.jun.fm.domain.club;

import com.jun.fm.domain.player.Player;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Entity
public class Club {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToOne
	private Player owner;

	@OneToMany(mappedBy = "club")
	private List<Player> players;

}
