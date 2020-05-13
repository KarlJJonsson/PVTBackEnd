package com.group158.UrbanAdventure.Models;

import java.util.List;

public class AdventurePart {
    private int index;
    private Location location;
    private List<Event> events;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int part) {
        this.index = part;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public AdventurePart(int index, Location location, List<Event> events){
        this.index = index;
        this.location = location;
        this.events = events;
    }
}
