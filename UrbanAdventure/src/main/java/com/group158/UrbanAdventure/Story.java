package com.group158.UrbanAdventure;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")
public class Story {
    @Id //Spring framework id
    private String id;

    //Icon
    //Creator ID
    //Theme
    //tags eller kategori

    private String title;
    private List<Event> events;
    private String description;
    private int firstEventIndex; //ID för första eventet dvs. startskärmen för story.

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