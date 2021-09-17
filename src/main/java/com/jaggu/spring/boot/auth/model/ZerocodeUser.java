package com.jaggu.spring.boot.auth.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ZerocodeUser extends User {

	private static final long serialVersionUID = 7374238885011686579L;

	private String firstName;
	private String lastName;

	public ZerocodeUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName,
			String lastName) {
		super(username, password, authorities);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public ZerocodeUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String firstName, String lastName) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object rhs) {
		return super.equals(rhs);
	}

}