package com.group158.UrbanAdventure.Models;

public class EventLocation extends Event{
    Location location;
    boolean visible;
    boolean triggered;
    boolean despawn;
    boolean currentlyActive;
    
    public boolean getTriggered() {
        return this.triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    public boolean getDespawn() {
        return this.despawn;
    }

    public void setDespawn(boolean despawn) {
        this.despawn = despawn;
    }

    public boolean getCurrentlyActive() {
        return this.currentlyActive;
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public EventLocation(int index, int path, Location location, boolean visible, boolean triggered, boolean despawn, boolean currentlyActive) {
        super(index, path);
        this.location = location;
        this.visible = visible;
        triggered = false;
        currentlyActive = false;
        despawn = false;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventLocation)) {
            return false;
        }
        EventLocation eventLocation = (EventLocation) o;
        return (this.location.equals(eventLocation.location) && this.visible == eventLocation.visible);
    }

    
}