package com.group158.UrbanAdventure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import org.springframework.data.annotation.Id;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@JsonSubTypes({
    @Type(value = Location.class, name = "Location"),
    @Type(value = Message.class, name = "Message")
})
public abstract class Event {

    //möjligen en icon för events?

    //id ska vara unikt per story, inte globalt som nu.
    @Id
    private int id;

    List<Event> children;

    public Event(List<Event> children){
        this.children = children;
    }
}