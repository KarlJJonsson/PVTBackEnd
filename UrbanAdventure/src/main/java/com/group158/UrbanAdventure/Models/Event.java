package com.group158.UrbanAdventure.Models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@JsonSubTypes({
    @Type(value = EventPrompt.class, name = "prompt"),
    @Type(value = EventMessage.class, name = "message"),
    @Type(value = EventMultipleChoice.class, name = "multipleChoice"),
    @Type(value = EventEnd.class, name = "end"),
    @Type(value = EventItemReceive.class, name = "itemReceive"),
    @Type(value = EventLocation.class, name = "location")
})

public abstract class Event {
    private int index;
    private int path;

    public Event(int index, int path){
        this.index = index;
        this.path = path;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getPath(){
        return path;
    }

    public void setPath(int path){
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return index == event.index && path == event.path;
    }
}