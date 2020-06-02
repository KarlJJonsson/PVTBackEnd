package com.group158.UrbanAdventure.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    //nedan används två olika classen vid namn User, därmed lite förvirrande. Har kommenterat dessa rader
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        com.group158.UrbanAdventure.User.User user; //Vår egen User class
        Optional<com.group158.UrbanAdventure.User.User> response = userRepository.findByEmail(username);
        System.out.println(username+" was found");
        if(response.isPresent()) {
            System.out.println(username+" was authenticated");
            user = response.get();
            List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER")); 
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities); //spring security egen User class
        }
        else{
            System.out.println(username+" was not authenticated");
            throw new UsernameNotFoundException(String.format("User with name '%s' was not found", username));
        }
    }
}