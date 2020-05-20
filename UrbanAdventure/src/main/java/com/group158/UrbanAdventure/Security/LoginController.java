package com.group158.UrbanAdventure.Security;

import com.group158.UrbanAdventure.User.AppUser;
import com.group158.UrbanAdventure.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/authenticate")
@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> postMethodName(@RequestBody AppUser user) {
        userRepository.save(user);
        return new ResponseEntity<String>("User registered", HttpStatus.OK) ;
    }
    
}