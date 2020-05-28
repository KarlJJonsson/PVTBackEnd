package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import com.group158.UrbanAdventure.User.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void sameEmailShouldBeEqual(){
        //Generate 2 identical users
        User user1 = testUtil.generateUser();
        User user2 = testUtil.generateUser();

        //create a map to change one users votes
        Map<String, Integer> votes = new HashMap<String, Integer>();
        votes.put("changed key", 1);

        //change all values for user1 except email
        user1.setName("Changed name");
        user1.setPassword("changed password");
        user1.setVotes(votes);

        assertEquals(user1, user2);
    }

    @Test
    public void differentEmailShouldNotBeEqual(){
        //Generate 2 identical users
        User user1 = testUtil.generateUser();
        User user2 = testUtil.generateUser();

        //change email of user1 but leave all other fields identical
        user1.setEmail("changedEmail@email.com");

        assertNotEquals(user1, user2);
    }

    
}