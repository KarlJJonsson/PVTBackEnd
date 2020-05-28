package com.group158.UrbanAdventure.Security;

import java.util.List;

import com.group158.UrbanAdventure.User.User;
import com.group158.UrbanAdventure.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return new ResponseEntity<String>("User with email '"+user.getEmail()+"' already exists!", HttpStatus.FORBIDDEN);
        }
        else{
            userRepository.save(user);
            return new ResponseEntity<String>("New user '"+user.getEmail()+"' created!", HttpStatus.CREATED);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //ta bort denna efter testning
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTestUser")
    public ResponseEntity<String> removeUser(){
        User user = new User("test@mail.com", "Test Person", "password");

        User foundUser = userRepository.findByEmail(user.getEmail()).get();
        
        userRepository.delete(foundUser);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}