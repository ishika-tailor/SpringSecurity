package com.ishika.ProductManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail implements UserDetails{

	private String username;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authority;

	public MyUserDetail(User user) {
		
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.enabled=user.isEnabled();
		this.authority=Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority :: new)
				.collect(Collectors.toList());
	}
	public MyUserDetail() {
	
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return enabled;
	}

	
	
}
