package com.group158.UrbanAdventure;

import java.util.List;

@Deprecated
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

    @Override
    public boolean equals(Object object){
        if (object instanceof AdventurePart){
            AdventurePart adventurePart = (AdventurePart) object;
            if(this.index == (adventurePart.index)
                && this.location.equals(adventurePart.location)
                && this.events.equals(adventurePart.events)
            ){
                return true;
            }
        }
        return false;

    }
}
