package com.group158.UrbanAdventure.Models;

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
}