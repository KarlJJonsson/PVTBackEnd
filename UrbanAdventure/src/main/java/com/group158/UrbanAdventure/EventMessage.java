package com.group158.UrbanAdventure;

public class EventMessage extends Event {
    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public EventMessage(int id, int path, String message){
        super(id, path);
        this.message = message;
    }
}