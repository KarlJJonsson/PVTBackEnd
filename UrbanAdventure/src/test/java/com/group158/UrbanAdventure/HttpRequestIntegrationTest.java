package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.group158.UrbanAdventure.User.User;
import com.group158.UrbanAdventure.User.UserRepository;

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
    UserRepository userRepository;

    

    // @BeforeEach
    // public void preReq(){
    //     Adventure adventure = testUtil.generateAdventure();
    //     userRepository.save(testUtil.generateAuthUser());
    // }
    // @AfterEach
    // public void postReq(){
    //     userRepository.delete(testUtil.generateAuthUser());
    // }

    @Test //tests /api/create endpoint
    public void createAdventureTest(){
        headers = testUtil.generateAuthorizedHeader();
        //post adventure to database
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        ResponseEntity<String> response = this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);
        
        //extracts adventure ID and statusCode
        String body = response.getBody(); //returns ID for posted adventure
        HttpStatus status = response.getStatusCode();

        assertEquals(HttpStatus.CREATED, status, "/api/create doesn't return correct statuscode");
        assertEquals(body, adventure.getId(), "local and posted id is different for same adventure");

        //deletes adventure when test is done
        adventureRepository.delete(adventure);
    }

    @Test //tests that /api/create correctly puts adventure in database
    public void createdAdventureShouldBeCorrect(){
        headers = testUtil.generateAuthorizedHeader();
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);

        //get request for same adventure as above request
        ResponseEntity<Adventure> getResponse = this.restTemplate.exchange("/api/get/"+adventure.getId(), HttpMethod.GET, entity, Adventure.class);

        //extract adventure from getResponse
        Adventure adventureFromDatabase = getResponse.getBody();

        assertEquals(true, adventureFromDatabase.isIdentical(adventure), "local adventure is different from posted on in DB");

        //deletes adventure when test is done
        adventureRepository.delete(adventure);
    }

    @Test //tests /api/remove/{id}
    public void deleteAdventureTest(){
        //post 1 adventure to database
        headers = testUtil.generateAuthorizedHeader();
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);

        //get request to ensure above post worked correctly
        ResponseEntity<Adventure> response1 = this.restTemplate.exchange("/api/get/"+adventure.getId(), HttpMethod.GET, entity, Adventure.class);

        //extracts adventure from response1
        Adventure response1Adventure = response1.getBody();

        //Delete request to database for same Adventure as the above 2 requests
        this.restTemplate.exchange("/api/remove/"+adventure.getId(), HttpMethod.DELETE, entity, String.class);

        //get request to ensure Adventure has been deleted
        ResponseEntity<Adventure> response2 = this.restTemplate.exchange("/api/get/"+adventure.getId(), HttpMethod.GET, entity, Adventure.class);

        //extracts adventure and code from last get request, these should be null and 404
        Adventure response2Adventure = response2.getBody();
        HttpStatus response2Status = response2.getStatusCode();

        assertEquals(true, adventure.isIdentical(response1Adventure), "posted and local adventure is not identical");
        assertEquals(response2Adventure, null, "adventure has not been correctly deleted");
        assertEquals(HttpStatus.NOT_FOUND, response2Status, "adventure has not been correctly deleted - or endpoints returns faulty HttpStatusCode");
    }

    @Test //tests /api/get/{id} 
    public void getAdventureByIdTest(){
        //post 1 adventure to database
        headers = testUtil.generateAuthorizedHeader();
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);

        //get request to /api/get{id}
        ResponseEntity<Adventure> response = this.restTemplate.exchange("/api/get/"+adventure.getId(), HttpMethod.GET, entity, Adventure.class);

        // extracts adventure and statusCode
        Adventure responseAdventure = response.getBody();
        HttpStatus responseStatus = response.getStatusCode();

        assertEquals(true, responseAdventure.isIdentical(adventure), "endpoint doesn't return correct adventure");
        assertEquals(HttpStatus.OK, responseStatus, "endpoints doesn't respond with correct HttpStatusCode");

        //deletes adventure when test is done
        adventureRepository.delete(adventure);
    }

    @Test //tests api/search/{adventureTitle}
    public void getAllAdventureTitleShouldReturnListOfAdventure(){
        //posts 1 adventure to database
        headers = testUtil.generateAuthorizedHeader();
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        String id = this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class).getBody();

        //get request to api/search/{adventureTitle}
        ResponseEntity<?> response = this.restTemplate.exchange("/api/search/"+adventure.getAdventureTitle(), HttpMethod.GET, entity, List.class);

        //extracts Adventure in JSON die to responseType for request being List. Extracts list size and statusCode from response
        String responseJsonAdventure = ((List<?>) response.getBody()).get(0).toString();
        int responseListSize = ((List<?>)response.getBody()).size();
        HttpStatus responseStatus = response.getStatusCode();

        // generates an Adventure in JSON due to responseType being List (Not possible to extract Adventure from Object)
        String jsonAdventure = testUtil.generateAdventureJsonStringWithId(id);

        assertEquals(jsonAdventure, responseJsonAdventure, "local and received jsonAdventure is not identical");
        assertEquals(responseListSize>0, true, "received list is empty - it shouldn't be");
        assertEquals(responseStatus, HttpStatus.OK, "received HttpStatusCode is not correct");

        //deletes adventure when test is done
        adventureRepository.delete(adventure);
    }

    @Test //tests api/all
    public void getAllShouldReturnAllAdventures(){

        //creates 3 adventures
        adventure.setAdventureTitle("OnlyForTesting1");

        Adventure adventure2 = testUtil.generateAdventure();
        adventure2.setAdventureTitle("OnlyForTesting2");

        Adventure adventure3 = testUtil.generateAdventure();
        adventure3.setAdventureTitle("OnlyForTesting3");
        
        headers = testUtil.generateAuthorizedHeader();

        //posts adventures to database
        HttpEntity<Adventure> entity = new HttpEntity<>(adventure, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);
        entity = new HttpEntity<>(adventure2, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);
        entity = new HttpEntity<>(adventure3, headers);
        this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);

        //get request to /api/all
        ResponseEntity<?> response = this.restTemplate.exchange("/api/all", HttpMethod.GET, entity, List.class);

        // extracts size of list and statusCode
        int responseListSize = ((List<?>) response.getBody()).size();
        HttpStatus responseStatus = response.getStatusCode();

        assertEquals(responseListSize>=3, true, "received list dos not contain all adventures");
        assertEquals(responseStatus, HttpStatus.OK, "received HttpStatusCode i not correct");

        //deletes adventures when test is done
        adventureRepository.delete(adventure);
        adventureRepository.delete(adventure2);
        adventureRepository.delete(adventure3);
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

        headers = testUtil.generateAuthorizedHeader();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);

        HttpEntity<Map<String, Integer>> entity= new HttpEntity<Map<String, Integer>>(newRating, headers);

        ResponseEntity<String> createResponse = this.restTemplate.exchange("/api/create", HttpMethod.POST, entity, String.class);
        String adventureId = createResponse.getBody();


        ResponseEntity<String> patchResponse = apacheRestTemplate.exchange("/api/update/"+adventureId+"/rating", HttpMethod.PATCH, entity, String.class);

        Adventure adventure = adventureRepository.findById(adventureId).get();

        Map<String, Integer> actualRating = Map.of(
            "upvote", adventure.getThumbsUp(),
            "downvote", adventure.getThumbsDown()
        );
                
        assertEquals(HttpStatus.OK, patchResponse.getStatusCode(), "received HttpStatusCode is not correct");
        assertEquals(newRating, actualRating, "rating has not patched correctly");

        adventureRepository.delete(adventure);
    }

    @Test
    public void creatingNewUserAndDeletingIt(){
        ResponseEntity<String> response = this.restTemplate.postForEntity("/auth/create", user, String.class);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "new user was not successfully created");

        HttpEntity<Object> request = new HttpEntity<>(headers);

        HttpStatus responseStatus = this.restTemplate.exchange("/auth/deleteTestUser", HttpMethod.DELETE, request, String.class).getStatusCode();

        assertEquals(HttpStatus.OK, responseStatus, "deleting testUser was not successfully done");
    }

    @Test
    public void createdUserShouldBeAuthenticated(){

        user = testUtil.generateUser(); //återställer user då password blir encodat i create endpointen

        ResponseEntity<String> response = this.restTemplate.postForEntity("/auth/create", user, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "new user was not successfully created");

        user = testUtil.generateUser(); //återställer user då password blir encodat i create endpointen

        String authStr = user.getEmail()+":"+user.getPassword();
        String encodedAuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());
        String headerStr = "Basic "+encodedAuthStr;

        headers.set("Authorization", headerStr);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<User> loginResponse = this.restTemplate.exchange("/auth/login", HttpMethod.GET, request, User.class);
        HttpStatus loginResponseStatus = loginResponse.getStatusCode();

        assertEquals(HttpStatus.OK, loginResponseStatus, "login was not accepted");
        assertEquals(user, loginResponse.getBody(), "correct user was not returned after login");

        HttpStatus deleteResponse = this.restTemplate.exchange("/auth/deleteTestUser", HttpMethod.DELETE, request, String.class).getStatusCode();

        assertEquals(HttpStatus.OK, deleteResponse, "deleting testUser was not successfully done");
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

        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus, "Unauthorized user was not deemed unauthorized");
    }

    @Test
    public void userVotesShouldPatch(){

        //put new user in database
        User user = testUtil.generateUser();
        this.restTemplate.postForEntity("/auth/create", user, String.class);

        //create apache HttpClient for RestTemplate, since Spring boot dont support patch.
        this.apacheRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.apacheRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

        //creatng headers for authentication
        headers = testUtil.generateAuthorizedHeaderFromUser(user);

        //create new map to user for the patch
        Map<String, Integer> newVotes = Map.of(
            "AdventureId", 1
        );

        //create httpEntity with authorized headers and the newVotes map
        HttpEntity<Map<String, Integer>> entity= new HttpEntity<Map<String, Integer>>(newVotes, headers);

        //perform patchRequest
        ResponseEntity<User> patchResponse = apacheRestTemplate.exchange("/user/update/rating", HttpMethod.PATCH, entity, User.class);

        //get user from response
        User userFromResponse = patchResponse.getBody();

        //set actual votes from user from response
        Map<String, Integer> actualVotes = userFromResponse.getVotes();

        //delete posted user from DB, in order to not clutter DB from testing
        this.restTemplate.exchange("/auth/deleteTestUser", HttpMethod.DELETE, entity, String.class);

                
        assertEquals(HttpStatus.OK, patchResponse.getStatusCode(), "patchRequest was not done successfully");
        assertEquals(newVotes, actualVotes, "endpoint did not patch resource");
    }

    @Test
    public void EndpointsShouldBeLocked(){

        HttpHeaders unAuthHeaders = new HttpHeaders();
        HttpEntity<?> entity1= new HttpEntity<>(unAuthHeaders);
        HttpHeaders authHeaders = testUtil.generateAuthorizedHeader();
        HttpEntity<?> entity2= new HttpEntity<>(adventure, authHeaders);

        String id = this.restTemplate.exchange("/api/create", HttpMethod.POST, entity2, String.class).getBody();

        HttpStatus response1 = this.restTemplate.exchange("/auth/login", HttpMethod.GET, entity1, Object.class).getStatusCode();
        HttpStatus response2 = this.restTemplate.exchange("/api/all", HttpMethod.GET, entity1, Object.class).getStatusCode();
        HttpStatus response3 = this.restTemplate.exchange("/api/create", HttpMethod.POST, entity1, Object.class).getStatusCode();
        HttpStatus response5 = this.restTemplate.exchange("/api/search/"+adventure.getAdventureTitle(), HttpMethod.GET, entity1, Object.class).getStatusCode();
        HttpStatus response6 = this.restTemplate.exchange("/api/get/"+id, HttpMethod.GET, entity1, Object.class).getStatusCode();
        HttpStatus response7 = this.restTemplate.exchange("/api/update/"+id+"/rating", HttpMethod.GET, entity1, Object.class).getStatusCode();
        HttpStatus response4 = this.restTemplate.exchange("/api/remove/"+id, HttpMethod.DELETE, entity1, Object.class).getStatusCode();
        HttpStatus response8 = this.restTemplate.exchange("/user/update/rating", HttpMethod.GET, entity1, Object.class).getStatusCode();

        adventureRepository.delete(adventure);

        assertEquals(HttpStatus.UNAUTHORIZED, response1, "/auth/login not locked");
        assertEquals(HttpStatus.UNAUTHORIZED, response2, "/api/all not locked");
        assertEquals(HttpStatus.UNAUTHORIZED, response3, "/api/create not locked");
        assertEquals(HttpStatus.UNAUTHORIZED, response4, "/api/remove/{id} not locked- url was /api/get/"+id);
        assertEquals(HttpStatus.UNAUTHORIZED, response5, "/api/search/{title} not locked");
        assertEquals(HttpStatus.UNAUTHORIZED, response6, "/api/get/{id} not locked - url was /api/get/"+id);
        assertEquals(HttpStatus.UNAUTHORIZED, response7, "/api/update/{id}/rating");
        assertEquals(HttpStatus.UNAUTHORIZED, response8, "/user/update/rating not locked");
    }

}