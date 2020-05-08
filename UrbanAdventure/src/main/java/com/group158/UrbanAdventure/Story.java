package com.group158.UrbanAdventure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")
public class Story {
    private String id; //tog bort annoteringen @id, den försökte göra om string till int, vet ej orsak men någonting under the hood som bråkade

    //Icon
    //Creator ID
    //Theme
    //tags eller kategori

    private String title;
    private List<Event> events;
    private String description;
    private int firstEventIndex; //ID för första eventet dvs. startskärmen för story.

    public Story(String title, String description){
        this.id = UUID.randomUUID().toString(); //Genereras med UUID, dvs en universal unique pseudo random generator
        this.title = title;
        this.description = description;
    }

    public Story(String title, String description, int firstEventIndex, List<Event> events){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.firstEventIndex = firstEventIndex;
        this.events = events;
    }

    public Story(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setFirst(int firstEventIndex){
        this.firstEventIndex = firstEventIndex;
    }

    public int getFirst(){
        return firstEventIndex;
    }

    public List<Event> getEvents(){
        return events;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void setEvents(List<Event> events){
        this.events = events;
    }

    public Event getEvent(int index){
        return events.get(index);
    }
}