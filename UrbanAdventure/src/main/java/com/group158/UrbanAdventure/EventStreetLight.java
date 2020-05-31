package com.group158.UrbanAdventure;

import java.util.Objects;

public class EventStreetLight extends Event {
    private Location location;
    private int delay;

    public EventStreetLight(int index, int path, Location location, int delay) {
        super(index, path);
        this.location = location;
        this.delay = delay;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventStreetLight)) {
            return false;
        }
        EventStreetLight eventStreetLight = (EventStreetLight) o;
        return Objects.equals(location, eventStreetLight.location) 
        && delay == eventStreetLight.delay
        && this.getIndex() == eventStreetLight.getIndex()
        && this.getPath() == eventStreetLight.getPath();
    }


    
}