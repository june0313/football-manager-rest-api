package com.jun.fm.service.user_details;

import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by wayne on 2017. 5. 28..
 *
 */
public class User implements UserDetails {

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public User(Player player) {
		this.username = player.getName();
		this.password = player.getPassword();
		this.authorities = player.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(toList());
	}

	public static User from(Player player) {
		return new User(player);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
