package com.group158.UrbanAdventure.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {
    
    public String name;
    @Id
    public String email;
    public String password;
    
    public User() {}
    
    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}