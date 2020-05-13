package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    Testutilities testUtil = new Testutilities();
    Adventure adventure = testUtil.generateAdventure();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createAdventureTest(){
        ResponseEntity<String> response = this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);
        String body = response.getBody();
        HttpStatus status = response.getStatusCode();
        assertEquals(HttpStatus.CREATED, status, "checks statuscode for creation");
        assertEquals(body, adventure.getId(), "checks returned id is same as adventure id");

        //deletes adventure when test is done
        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());
    }

    @Test
    public void createdAdventureShouldBeCorrect(){
        this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);

        ResponseEntity<Adventure> getResponse = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
            Adventure.class);

        Adventure adventureFromDatabase = getResponse.getBody();

        assertEquals(adventure, adventureFromDatabase, "checks that adventure exists in database and is correct");

        //deletes adventure when test is done
        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());
    }

    @Test
    public void deleteAdventureTest(){
        this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);

        ResponseEntity<Adventure> response1 = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
        Adventure.class);

        Adventure response1Adventure = response1.getBody();

        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());

        ResponseEntity<Adventure> response2 = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
            Adventure.class);

        Adventure response2Adventure = response2.getBody();
        HttpStatus response2Status = response2.getStatusCode();

        assertEquals(response1Adventure, adventure, "checks if adventure has been posted correctly");
        assertEquals(response2Adventure, null, "checks if adventure has been deleted correctly");
        assertEquals(HttpStatus.NOT_FOUND, response2Status, "checks that httpstatus correctly responds with 404");
    }

    @Test
    public void getAdventureByIdTest(){
        this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);

        ResponseEntity<Adventure> response = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
            Adventure.class);

        Adventure responseAdventure = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertEquals(adventure, responseAdventure, "checks that endpoint responds with correct adventure");
        assertEquals(HttpStatus.OK, responseStatus, "checks that endpoint responds with correct Httpstatus");

        //deletes adventure when test is done
        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());
    }

    @Test
    public void getAllAdventureTitleShouldReturnListOfAdventure(){
        this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);

        ResponseEntity<List> response = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/search/"+adventure.getAdventureTitle(),
            List.class);

        String responseJsonAdventure = response.getBody().get(0).toString();
        int responseListSize = response.getBody().size();
        HttpStatus responseStatus = response.getStatusCode();

        String jsonAdventure = testUtil.generateAdventureJsonStringWithId(adventure.getId());

        assertEquals(jsonAdventure, responseJsonAdventure, "checks that received jsonAdventure is correct");
        assertEquals(responseListSize>0, true, "checks that received List is larger than 0");
        assertEquals(responseStatus, HttpStatus.OK, "checks that received status is correct");

        //deletes adventure when test is done
        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());
    }
}