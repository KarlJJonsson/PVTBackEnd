package com.group158.UrbanAdventure;

import java.util.ArrayList;
import java.util.List;

public class Testutilities {
    public Testutilities(){};

    public List<Adventure> generateAdventureList(){
        //create List
        List<Adventure> adventures = new ArrayList<Adventure>();

        //populate List
        adventures.add(generateAdventure());
        adventures.add(generateAdventure());
        adventures.add(generateAdventure());

        //return List
        return adventures;
    }

    public Adventure generateAdventure(){

        //create List for AdventurePart index 0
        List<Event> events = new ArrayList<Event>();

        //populate List and create Events
        events.add(new EventLocation(0, 1, new Location(30, 30.5, 30.5), true));
        events.add(new EventPrompt(1, 2, "Test message.", "Test promptMessage.", "Test correctAnswer.", 1));
        events.add(new EventMessage(2, 0, "Test message."));

        //create Adventure and set Parts
        Adventure adventure = new Adventure("OnlyForTesting", 2, "genre", "DescriptiveText.", "Anonymous", events, 0, 0);

        //return Adventure
        return adventure;
    }

    public String generateAdventureJsonStringWithId(String id){
        String adventureJsonString = String.format("{id=%s, adventureTitle=OnlyForTesting, length=2, genre=genre, "+
        "descriptionText=DescriptiveText., author=Anonymous, events=[{type=location, index=0, path=1, location={latitude=30.5, "+
        "longitude=30.5, radius=30}, visible=true}, {type=prompt, index=1, path=2, message=Test message., "+
        "promptMessage=Test promptMessage., correctAnswer=Test correctAnswer., wrongAnswerPath=1}, {type=message, index=2, path=0, "+
        "message=Test message.}], thumbsUp=0, thumbsDown=0}", id);
        return adventureJsonString;
    }
}