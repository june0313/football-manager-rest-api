package com.jun.fm.domain.club;

import com.google.common.collect.Lists;
import com.jun.fm.domain.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
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

	public void addPlayer(Player player) {
		if (players == null) {
			players = Lists.newArrayList();
		}

		players.add(player);
	}

}
