package com.group158.UrbanAdventure.Models;

public class EventLast extends Event{
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
}