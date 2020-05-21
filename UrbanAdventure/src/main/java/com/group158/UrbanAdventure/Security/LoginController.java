package com.group158.UrbanAdventure.Security;

import java.util.List;

import com.group158.UrbanAdventure.User.AppUser;
import com.group158.UrbanAdventure.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @PostMapping("/create")
    public ResponseEntity<String> createAdventure(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<String>(user.getEmail(), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        return new ResponseEntity<List<AppUser>>(users, HttpStatus.OK);
    }
    
}