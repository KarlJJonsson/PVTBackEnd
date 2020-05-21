package com.group158.UrbanAdventure.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AppUser user;
        Optional<AppUser> response = userRepository.findByEmail(username);
        if(response.isPresent()) {
            user = response.get();
            List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER")); 
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        else{
            System.out.println(username);
            throw new UsernameNotFoundException(String.format("User with name '%s' was not found", username));
        }
    }
}