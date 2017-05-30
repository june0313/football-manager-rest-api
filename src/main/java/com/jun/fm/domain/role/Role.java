package com.jun.fm.domain.role;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wayne on 2017. 5. 29..
 *
 */
@Entity
@Getter
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	Role(String name) {
		this.name = name;
	}

	public static Role of(String name) {
		return new Role(name);
	}

}
