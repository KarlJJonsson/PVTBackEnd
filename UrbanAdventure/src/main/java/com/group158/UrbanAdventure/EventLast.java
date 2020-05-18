package com.group158.UrbanAdventure;

@Deprecated
public class EventLast extends Event {
    private String message;
    private int nextPartIndex;

    public EventLast(int index, int path, String message, int nextPartIndex) {
        super(index, path);
        this.message = message;
        this.nextPartIndex = nextPartIndex;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNextPartIndex() {
        return this.nextPartIndex;
    }

    public void setNextPartIndex(int nextPartIndex) {
        this.nextPartIndex = nextPartIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventLast)) {
            return false;
        }
        EventLast event = (EventLast) o;
        return this.getIndex() == event.getIndex() 
        && this.getPath() == event.getPath() 
        && this.message.equals(event.getMessage())
        && this.nextPartIndex == event.getNextPartIndex();
    }
}