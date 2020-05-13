package com.group158.UrbanAdventure;

public class EventItemReceive extends Event {
    private String message;
    private String item;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public EventItemReceive(int index, int path, String message, String item) {
        super(index, path);
        this.message = message;
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventItemReceive)) {
            return false;
        }
        EventItemReceive event = (EventItemReceive) o;
        return this.getIndex() == event.getIndex() 
        && this.getPath() == event.getPath() 
        && this.message.equals(event.getMessage())
        && this.item.equals(event.getItem());
    }
}