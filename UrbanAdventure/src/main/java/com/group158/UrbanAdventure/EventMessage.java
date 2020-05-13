package com.group158.UrbanAdventure;

public class EventMessage extends Event {
    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public EventMessage(int index, int path, String message){
        super(index, path);
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventMessage)) {
            return false;
        }
        EventMessage event = (EventMessage) o;
        return this.getIndex() == event.getIndex() 
        && this.getPath() == event.getPath() 
        && this.message.equals(event.getMessage());
    }
}