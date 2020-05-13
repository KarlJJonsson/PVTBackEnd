package com.group158.UrbanAdventure;

public class EventItemReceive extends Event{
    private String message;
    private Item item;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public EventItemReceive(int index, int path, String message, Item item) {
        super(index, path);
        this.message = message;
        this.item = item;
    }
}