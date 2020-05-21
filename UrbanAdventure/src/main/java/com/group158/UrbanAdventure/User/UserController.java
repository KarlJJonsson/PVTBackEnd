package com.group158.UrbanAdventure.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
   /* @Autowired
    private UserService userService;

    @PostMapping
    public Authentication login(@RequestBody @Valid LoginDto loginDto){
        return userService.signin(loginDto.getUserName(),  loginDto.getPassword());
    }*/
}
