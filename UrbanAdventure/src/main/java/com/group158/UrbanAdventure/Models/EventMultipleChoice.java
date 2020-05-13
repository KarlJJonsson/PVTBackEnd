package com.group158.UrbanAdventure.Models;

import java.util.List;

public class EventMultipleChoice extends Event{
    private List<Choice> choices;
    private String message;

    public EventMultipleChoice(int index, int path, List<Choice> choices, String message) {
        super(index, path);
        this.choices = choices;
        this.message = message;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventMultipleChoice choices(List<Choice> choices) {
        this.choices = choices;
        return this;
    }

    public EventMultipleChoice message(String message) {
        this.message = message;
        return this;
    }
}