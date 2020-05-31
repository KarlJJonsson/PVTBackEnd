package com.group158.UrbanAdventure;

public class EventLocation extends Event{
    private Location location;
    private boolean visible;
    private boolean triggered;
    private boolean despawn;
    private boolean currentlyActive;
    
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
        this.triggered = triggered;
        this.currentlyActive = currentlyActive;
        this.despawn = despawn;
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
        return ((this.location.equals(eventLocation.location) 
        && this.visible == eventLocation.visible)
        && this.getIndex() == eventLocation.getIndex()
        && this.getPath() == eventLocation.getPath()
        && this.triggered == eventLocation.getTriggered()
        && this.despawn == eventLocation.despawn
        && this.currentlyActive == eventLocation.currentlyActive
        );
    }

    
}