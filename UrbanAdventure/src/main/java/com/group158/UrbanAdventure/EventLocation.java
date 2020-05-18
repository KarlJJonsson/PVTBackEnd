package com.group158.UrbanAdventure;

public class EventLocation extends Event{
    Location location;
    boolean visible;
    boolean triggered;
    boolean despawn;
    boolean currentlyActive;

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public EventLocation(int index, int path, Location location, boolean visible) {
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