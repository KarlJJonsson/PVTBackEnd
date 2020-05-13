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
        events.add(new EventPrompt(0, 1, "Test message.", "Test promptMessage.", "Test correctAnswer", 0));
        events.add(new EventMessage(1, 0, "Test message"));
        
        //create List for Adventure
        List<AdventurePart> parts = new ArrayList<AdventurePart>();

        //populate List, create AdventureParts and set events
        parts.add(new AdventurePart(0, new Location(30, 30.5, 30.5), events));
        parts.add(new AdventurePart(1, new Location(20, 40.5, 40.5), events));

        //create Adventure and set Parts
        Adventure adventure = new Adventure("OnlyForTesting", 2, "genre", 2, "DescriptiveText", "Anonymous", parts);

        //return Adventure
        return adventure;
    }

    public String generateAdventureJsonString(){
        String adventureJsonString = "{id=fb228f2e-aa14-46e0-9041-91566ca2b60e, adventureTitle=OnlyForTesting,"+
            " length=2, genre=genre, numberOfParts=2, descriptionText=DescriptiveText, author=Anonymous,"+
            " parts=[{index=0, location={latitude=30.5, longitude=30.5, radius=0}, events=[{type=prompt,"+
            " index=0, path=1, message=Test message., promptMessage=Test promptMessage, correctAnswer=Test"+
            " correctAnswer, wrongAnswerPath=0}, {type=message, index=1, path=0, message=Test message}]},"+
            " {index=1, location={latitude=40.5, longitude=40.5, radius=0}, events=[{type=prompt, index=0,"+
            " path=1, message=Test message., promptMessage=Test promptMessage, correctAnswer=Test correctAnswer,"+
            " wrongAnswerPath=0}, {type=message, index=1, path=0, message=Test message}]}]}";
        return adventureJsonString;
    }
    public String generateAdventureJsonStringWithId(String id){
    String adventureJsonString = "{id="+id+", adventureTitle=OnlyForTesting,"+
        " length=2, genre=genre, numberOfParts=2, descriptionText=DescriptiveText, author=Anonymous,"+
        " parts=[{index=0, location={latitude=30.5, longitude=30.5, radius=0}, events=[{type=prompt,"+
        " index=0, path=1, message=Test message., promptMessage=Test promptMessage, correctAnswer=Test"+
        " correctAnswer, wrongAnswerPath=0}, {type=message, index=1, path=0, message=Test message}]},"+
        " {index=1, location={latitude=40.5, longitude=40.5, radius=0}, events=[{type=prompt, index=0,"+
        " path=1, message=Test message., promptMessage=Test promptMessage, correctAnswer=Test correctAnswer,"+
        " wrongAnswerPath=0}, {type=message, index=1, path=0, message=Test message}]}]}";
    return adventureJsonString;
    }
}