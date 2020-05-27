package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.group158.UrbanAdventure.User.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Class for testing endpoints. Integrationtest to ensure objects are passed correctly between REST and database.
 * Tests passes real objects between REST and database, mocking is not done here. Uses TestUtilities for resources.
 * 
 * Many tests relies on other endpoints than the primary one being tested. This could be bad, and these should be replaced
 * with direct operations done against the repository instead. Work in progress.
 */

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestIntegrationTest {

    //prerequisites
    Testutilities testUtil = new Testutilities();
    Adventure adventure = testUtil.generateAdventure();
    User user = testUtil.generateUser();
    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private RestTemplate apacheRestTemplate;

    @Autowired
    AdventureRepository adventureRepository;

    @Test //tests /api/create endpoint
    public void createAdventureTest(){
        //post adventure to database
        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/create", adventure, String.class);
        
        //extracts adventure ID and statusCode
        String body = response.getBody(); //returns ID for posted adventure
        HttpStatus status = response.getStatusCode();

        assertEquals(HttpStatus.CREATED, status, "checks statuscode for creation");
        assertEquals(body, adventure.getId(), "checks returned id is same as adventure id");

        //deletes adventure when test is done
        this.restTemplate.delete("/api/remove/"+adventure.getId());
    }

    @Test //tests that /api/create correctly puts adventure in database
    public void createdAdventureShouldBeCorrect(){
        //post request
        this.restTemplate.postForEntity("/api/create", adventure, String.class);

        //get request for same adventure as above request
        ResponseEntity<Adventure> getResponse = this.restTemplate.getForEntity("/api/get/"+adventure.getId(),
            Adventure.class);

        //extract adventure from getResponse
        Adventure adventureFromDatabase = getResponse.getBody();

        assertEquals(adventure, adventureFromDatabase, "checks that adventure exists in database and is correct");

        //deletes adventure when test is done
        this.restTemplate.delete("/api/remove/"+adventure.getId());
    }

    @Test //tests /api/remove/{id}
    public void deleteAdventureTest(){
        //post 1 adventure to database
        this.restTemplate.postForEntity("/api/create", adventure, String.class);

        //get request to ensure above post worked correctly
        ResponseEntity<Adventure> response1 = this.restTemplate.getForEntity("/api/get/"+adventure.getId(),
        Adventure.class);

        //extracts adventure from response1
        Adventure response1Adventure = response1.getBody();

        //Delete request to database for same Adventure as the above 2 requests
        this.restTemplate.delete("/api/remove/"+adventure.getId());

        //get request to ensure Adventure has been deleted
        ResponseEntity<Adventure> response2 = this.restTemplate.getForEntity("/api/get/"+adventure.getId(),
            Adventure.class);

        //extracts adventure and code from delete request, these should be null and 404
        Adventure response2Adventure = response2.getBody();
        HttpStatus response2Status = response2.getStatusCode();

        assertEquals(response1Adventure, adventure, "checks if adventure has been posted correctly");
        assertEquals(response2Adventure, null, "checks if adventure has been deleted correctly");
        assertEquals(HttpStatus.NOT_FOUND, response2Status, "checks that httpstatus correctly responds with 404");
    }

    @Test //tests /api/get/{id} 
    public void getAdventureByIdTest(){
        //post 1 adventure to database
        this.restTemplate.postForEntity("/api/create", adventure, String.class);

        //get request to /api/get{id}
        ResponseEntity<Adventure> response = this.restTemplate.getForEntity("/api/get/"+adventure.getId(),
            Adventure.class);

        // extracts adventure and statusCode
        Adventure responseAdventure = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertEquals(adventure, responseAdventure, "checks that endpoint responds with correct adventure");
        assertEquals(HttpStatus.OK, responseStatus, "checks that endpoint responds with correct Httpstatus");

        //deletes adventure when test is done
        this.restTemplate.delete("/api/remove/"+adventure.getId());
    }

    @Test //tests api/search/{adventureTitle}
    public void getAllAdventureTitleShouldReturnListOfAdventure(){
        //posts 1 adventure to database
        this.restTemplate.postForEntity("/api/create", adventure, String.class);

        //get request to api/search/{adventureTitle}
        ResponseEntity<?> response = this.restTemplate.getForEntity("/api/search/"+adventure.getAdventureTitle(),
            List.class);
        

        //extracts Adventure in JSON die to responseType for request being List. Extracts list size and statusCode from response
        String responseJsonAdventure = ((List<?>) response.getBody()).get(0).toString();
        int responseListSize = ((List<?>)response.getBody()).size();
        HttpStatus responseStatus = response.getStatusCode();

        // generates an Adventure in JSON due to responseType being List (Not possible to extract Adventure from Object)
        String jsonAdventure = testUtil.generateAdventureJsonStringWithId(adventure.getId());

        assertEquals(jsonAdventure, responseJsonAdventure, "checks that received jsonAdventure is correct");
        assertEquals(responseListSize>0, true, "checks that received List is larger than 0");
        assertEquals(responseStatus, HttpStatus.OK, "checks that received status is correct");

        //deletes adventure when test is done
        this.restTemplate.delete("/api/remove/"+adventure.getId());
    }

    @Test //tests api/all
    public void getAllShouldReturnAllAdventures(){

        //creates 3 adventures
        adventure.setAdventureTitle("OnlyForTesting1");

        Adventure adventure2 = testUtil.generateAdventure();
        adventure2.setAdventureTitle("OnlyForTesting2");

        Adventure adventure3 = testUtil.generateAdventure();
        adventure3.setAdventureTitle("OnlyForTesting3");

        //posts adventures to database
        this.restTemplate.postForEntity("/api/create", adventure, String.class);
        this.restTemplate.postForEntity("/api/create", adventure2, String.class);
        this.restTemplate.postForEntity("/api/create", adventure3, String.class);

        //get request to /api/all
        ResponseEntity<?> response = this.restTemplate.getForEntity("/api/all/",
            List.class);

        // extracts size of list and statusCode
        int responseListSize = ((List<?>) response.getBody()).size();
        HttpStatus responseStatus = response.getStatusCode();

        assertEquals(responseListSize>=3, true, "checks that received List is equal or larger than 3");
        assertEquals(responseStatus, HttpStatus.OK, "checks that received status is correct");

        //deletes adventures when test is done
        this.restTemplate.delete("/api/remove/"+adventure.getId());
        this.restTemplate.delete("/api/remove/"+adventure2.getId());
        this.restTemplate.delete("/api/remove/"+adventure3.getId());
    }

    @Test
    public void adventureRatingShouldPatch(){

        /**
         * Måste använda en annan HttpClient för att testa patchning då springs "egna" inte stödjer detta.
         * Därför används Apaches istället just här. Pga att testRestTemplate ska vara fault-tolerant
         * tillåter det inte heller att ändra client, så ett RestTemplate måste användas direkt istället.
         */

        this.apacheRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.apacheRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

        Map<String, Integer> newRating = Map.of(
            "upvote", 2,
            "downvote", 5
        );

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);

        HttpEntity<Map<String, Integer>> entity= new HttpEntity<Map<String, Integer>>(newRating, headers);

        ResponseEntity<String> createResponse = this.restTemplate.postForEntity("/api/create", adventure, String.class);
        String adventureId = createResponse.getBody();


        ResponseEntity<String> patchResponse = apacheRestTemplate.exchange("/api/update/"+adventureId+"/rating", HttpMethod.PATCH, entity, String.class);

        Adventure adventure = adventureRepository.findById(adventureId).get();

        Map<String, Integer> actualRating = Map.of(
            "upvote", adventure.getThumbsUp(),
            "downvote", adventure.getThumbsDown()
        );
                
        assertEquals(HttpStatus.OK, patchResponse.getStatusCode());
        assertEquals(newRating, actualRating);

        adventureRepository.delete(adventure);
    }

    @Test
    public void creatingNewUserAndDeletingIt(){
        ResponseEntity<String> response = this.restTemplate.postForEntity("/auth/create", user, String.class);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        HttpEntity<Object> request = new HttpEntity<>(headers);

        HttpStatus responseStatus = this.restTemplate.exchange("/auth/deleteTestUser", HttpMethod.DELETE, request, String.class).getStatusCode();

        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void createdUserShouldBeAuthenticated(){

        user = testUtil.generateUser(); //återställer user då password blir encodat i create endpointen

        ResponseEntity<String> response = this.restTemplate.postForEntity("/auth/create", user, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        user = testUtil.generateUser(); //återställer user då password blir encodat i create endpointen

        String authStr = user.getEmail()+":"+user.getPassword();
        String encodedAuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());
        String headerStr = "Basic "+encodedAuthStr;

        headers.set("Authorization", headerStr);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        HttpStatus responseStatus = this.restTemplate.exchange("/auth/login", HttpMethod.GET, request, Object.class).getStatusCode();

        assertEquals(HttpStatus.OK, responseStatus);

        HttpStatus responseStatus2 = this.restTemplate.exchange("/auth/deleteTestUser", HttpMethod.DELETE, request, String.class).getStatusCode();

        assertEquals(HttpStatus.OK, responseStatus2);
    }

    @Test
    public void NonExistingUserShouldNotBeAuthenticated(){

        user = testUtil.generateAnotherUser(); //genererar en användare som inte finns i databasen
        String authStr = user.getEmail()+":"+user.getPassword();
        String encodedAuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());
        String headerStr = "Basic "+encodedAuthStr;

        headers.set("Authorization", headerStr);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        HttpStatus responseStatus = this.restTemplate.exchange("/auth/login", HttpMethod.GET, request, Object.class).getStatusCode();

        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus);
    }

}