package com.group158.UrbanAdventure.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        return new UserDetailsImpl(
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            authorities
        );
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDetailsImpl)) {
            return false;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return this.email.equals(userDetailsImpl.email);
    }
}