package com.group158.UrbanAdventure;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

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

    //id ska vara unikt per story, inte globalt som det är nu.
    private String id; //tog bort annoteringen @id, den försökte göra om string till int, vet ej orsak men någonting under the hood som bråkade


    List<Event> children;

    public Event(List<Event> children){
        this.id = UUID.randomUUID().toString();
        this.children = children;
    }

    public String getId(){
        return id;
    }
}