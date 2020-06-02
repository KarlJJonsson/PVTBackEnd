package com.group158.UrbanAdventure;

import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

import com.group158.UrbanAdventure.User.User;
/**
 * Utility for tests. This class is used to initialize objects and similar routines.
 * This is done to ensure stable replicable test throughout the project.
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
public class Testutilities {
    
    public Testutilities(){};

    public List<Adventure> generateAdventureList(){
        List<Adventure> adventures = new ArrayList<Adventure>();

        adventures.add(generateAdventure());
        adventures.add(generateAdventure());
        adventures.add(generateAdventure());

        return adventures;
    }

    public Adventure generateAdventure(){
        List<Event> events = new ArrayList<Event>();

        events.add(new EventLocation(0, 1, new Location(30, 30.5, 30.5), true, false, false, false));
        events.add(new EventPrompt(1, 2, "Test message.", "Test promptMessage.", "Test correctAnswer.", 1));
        events.add(new EventMessage(2, 0, "Test message."));

        Adventure adventure = new Adventure("OnlyForTesting", 2, "genre", "DescriptiveText.", "Anonymous", events, 0, 0);

        return adventure;
    }

    public Adventure generateAnotherAdventure(){
        List<Event> events = new ArrayList<Event>();

        events.add(generateEventLocation());
        events.add(generateEventStreetLight());

        Adventure adventure = new Adventure("OnlyForTesting", 2, "genre", "DescriptiveText.", "Anonymous", events, 0, 0);

        return adventure;
    }

    public String generateAdventureJsonStringWithId(String id){
        String adventureJsonString = String.format("{id=%s, adventureTitle=OnlyForTesting, length=2, genre=genre, "+
        "descriptionText=DescriptiveText., author=Anonymous, events=[{type=location, index=0, path=1, location={latitude=30.5, "+
        "longitude=30.5, radius=30}, visible=true, triggered=false, despawn=false, currentlyActive=false}, {type=prompt, index=1, path=2, message=Test message., "+
        "promptMessage=Test promptMessage., correctAnswer=Test correctAnswer., wrongAnswerPath=1}, {type=message, index=2, path=0, "+
        "message=Test message.}], thumbsUp=0, thumbsDown=0}", id);
        return adventureJsonString;
    }

    public User generateUser(){
        User user = new User("test@mail.com", "Test Person", "password");

        return user;
    }

    public User generateAnotherUser(){
        User user = new User("test2@mail.com", "Test Person2", "password");

        return user;
    }

    public EventStreetLight generateEventStreetLight(){
        EventStreetLight event = new EventStreetLight(1, 2, generateListOfStreetLights());

        return event;
    }

    public Location generateLocation(){
        Location location = new Location(5, 50.0, 40.0);

        return location;
    }

    public Location generateAnotherLocation(){
        Location location = new Location(3, 35.0, 13.0);

        return location;
    }

    public EventEnd generateEnd(){
        EventEnd event = new EventEnd(1, 2, "Test Message");

        return event;
    }

    public EventItemReceive generateItemReceive(){
        EventItemReceive event = new EventItemReceive(1, 2, "Test Message", "Test Item");

        return event;
    }

    public EventLocation generateEventLocation(){
        EventLocation event = new EventLocation(1, 2, generateLocation(), false, false, false, false);

        return event;
    }

    public EventMedia generateMedia(){
        EventMedia event = new EventMedia(1, 2, "ImageUrl", "SoundUrl", "VideoUrl");

        return event;
    }

    public EventMessage generateMessage(){
        EventMessage event = new EventMessage(1, 2, "Test Message");

        return event;
    }

    public EventPrompt generatePrompt(){
        EventPrompt event = new EventPrompt(1, 2, "Test Message", "Test Prompt Message", "Correct Answer", 3);

        return event;
    }

    public Choice generateChoice(){
        Choice choice = new Choice("Test text", 1);

        return choice;
    }
    
    public Choice generateAnotherChoice(){
        Choice choice = new Choice("Another Test Text", 2);

        return choice;
    }

    public EventMultipleChoice generateEventMultipleChoice(){
        List<Choice> choices = List.of(generateChoice(), generateAnotherChoice());
        EventMultipleChoice event = new EventMultipleChoice(1, 2, choices, "Test Message");

        return event;
    }

    public List<Choice> generateListOfChoices(){
        List<Choice> choices = List.of(generateChoice(), generateAnotherChoice(), generateChoice(), generateAnotherChoice());

        return choices;
    }

    public List<Choice> generateEmptyListOfChoices(){
        List<Choice> choices = List.of();

        return choices;
    }

    public StreetLight generateStreetLight(){
        StreetLight streetLight = new StreetLight(1, 1, 2, 2, 3);

        return streetLight;
    }

    public StreetLight generateAnotherStreetLight(){
        StreetLight streetLight = new StreetLight(2, 2, 3, 3, 4);

        return streetLight;
    }

    public List<StreetLight> generateListOfStreetLights(){
        List<StreetLight> streetLights = List.of(generateStreetLight(), generateAnotherStreetLight());

        return streetLights;
    }

    public List<StreetLight> generateAnotherListOfStreetLights(){
        List<StreetLight> streetLights = List.of(generateStreetLight(), generateAnotherStreetLight(), generateStreetLight(), generateAnotherStreetLight());

        return streetLights;
    }

    public HttpHeaders generateAuthorizedHeader(){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json");
        headers.setContentType(mediaType);

        String authStr = "AutoTest@test.com"+":"+"AutoTest";
        String encodedAuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());
        String headerStr = "Basic "+encodedAuthStr;

        headers.set("Authorization", headerStr);

        return headers;
    }

    public HttpHeaders generateAuthorizedHeaderFromUser(User user){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json");
        headers.setContentType(mediaType);

        String authStr = user.getEmail()+":"+user.getPassword();
        String encodedAuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());
        String headerStr = "Basic "+encodedAuthStr;

        headers.set("Authorization", headerStr);

        return headers;
    }

    public List<Event> generateEmptyList(){
        List<Event> list = List.of();

        return list;
    }

    public String generateValidName(){
        return "ValidName";
    }

    public String generateTooLongName(){
        return "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"; //60 characters long, max is 50
    }

    public String generateBlankString(){
        return "";
    }

    public String generateInvalidEmail(){
        return "notAnEmail";
    }

    public String generateValidEmail(){
        return "email@email.com";
    }

    public String generateTooLongEmail(){
        return "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb@email.com"; //valid email, but over 50 characters long
    }

    public String generateValidPassword(){
        return "validPass"; //has to be between 5 and 50 chars long
    }

    public String generateTooLongPassword(){
        return "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"; //60 characters long, max is 50
    }

    public String generateTooShortPassword(){
        return "bb"; //minimum is 5
    }
}