package com.group158.UrbanAdventure;

import java.util.List;

public class AdventurePart {
    private int part;
    private Location location;
    private List<Event> events;

    public int getPart() {
        return this.part;
    }

    public void setPart(int part) {
        this.part = part;
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

    public AdventurePart(int part, Location location, List<Event> events){
        this.part = part;
        this.location = location;
        this.events = events;
    }
}
