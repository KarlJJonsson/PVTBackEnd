package com.group158.UrbanAdventure.User;

import com.group158.UrbanAdventure.Adventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/")
public class UserController {
  @Autowired
  private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userRepository.save(user);
        System.out.println("{\n\n\n\n\n\n\n\n\n\n\n"+user.getEmail());
        return new ResponseEntity<>(user.getEmail(), HttpStatus.CREATED);
    }
  
  
   /* @Autowired
    private UserService userService;

    @PostMapping
    public Authentication login(@RequestBody @Valid LoginDto loginDto){
        return userService.signin(loginDto.getUserName(),  loginDto.getPassword());
    }*/

    
    
}
