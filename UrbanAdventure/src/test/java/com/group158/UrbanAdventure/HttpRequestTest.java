package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    String jsonAdventure = testUtil.generateAdventureJsonString();
    Adventure adventure = testUtil.generateAdventure();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createAdventureTest(){
        ResponseEntity<String> response = this.restTemplate.postForEntity("https://group8-15.pvt.dsv.su.se/api/create", adventure, String.class);
        String body = response.getBody();
        adventure.setId(body);
        jsonAdventure = testUtil.generateAdventureJsonStringWithId(body);
        HttpStatus status = response.getStatusCode();
        assertEquals(201, status, "checks statuscode for /create");
    }

    @Test
    public void createdAdventureShouldExist(){
        Adventure body = null;
        ResponseEntity<Adventure> response = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
            Adventure.class);
        
        if(response.getBody() != null){
            body = response.getBody();
        }
        else{
            fail("body was Null.");
        }

        assertEquals(adventure, body, "checks that adventure exists and is correct");
    }

    @Test
    public void deleteAdventureTest(){
        this.restTemplate.delete("https://group8-15.pvt.dsv.su.se/api/remove/"+adventure.getId());

        ResponseEntity<Adventure> response = this.restTemplate.getForEntity("https://group8-15.pvt.dsv.su.se/api/get/"+adventure.getId(),
            Adventure.class);

        assertEquals(false, response.hasBody(), "Deletes entry, and checks if it's removed from database.");
    }

    // @Test
    // public void getAllAdventureTitleShouldReturnListOfAdventure(){

    //     String expected = testUtil.generateAdventureJsonString();

    //     String actual = ((Map) this.restTemplate.getForObject("https://group8-15.pvt.dsv.su.se/api/search/OnlyForTesting",
    //         ArrayList.class).get(0)).toString();

    //     System.out.println(actual);

    //     assertEquals(expected, actual);
    // }
}