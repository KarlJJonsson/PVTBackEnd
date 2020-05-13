package com.group158.UrbanAdventure.Models;

public class EventEnd extends Event{
    private String message;

    public EventEnd(int index, int path, String message) {
        super(index, path);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}