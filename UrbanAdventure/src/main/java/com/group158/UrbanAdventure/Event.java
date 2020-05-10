package com.group158.UrbanAdventure;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@JsonSubTypes({
    @Type(value = EventPrompt.class, name = "prompt"),
    @Type(value = EventMessage.class, name = "message")
})

public abstract class Event {
    private int id;
    private int path;

    public Event(int id, int path){
        this.id = id;
        this.path = path;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPath(){
        return path;
    }

    public void setPath(int path){
        this.path = path;
    }
}