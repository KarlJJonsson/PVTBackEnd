package com.group158.UrbanAdventure.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {

    //constraint semantics for email, name and password has been moved to flutter. They are however left here without a validator, since a change back to java could be made
    //in the future. There is no validator implemented, so they should not be functioning. Even if they were, there would be no unwanted collision with the constraints
    //implemented in flutter.

    @NotBlank
    @Email
    @Size(max = 50)
    @Id
    private String email; //used as the Username

    @NotBlank
    @Size(max = 50)
    private String name; //name of the user, not a username

    @NotBlank
    @Size(min = 5, max = 50)
    private String password;

    Map<String, Integer> votes;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.votes = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getVotes(){
        return this.votes;
    }

    public void setVotes(Map<String, Integer> votes){
        this.votes = votes;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }

}