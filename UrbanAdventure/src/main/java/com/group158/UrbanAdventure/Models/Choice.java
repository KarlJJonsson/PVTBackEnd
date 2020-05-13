package com.group158.UrbanAdventure.Models;

public class Choice {
    private String text;
    private int path;

    public Choice() {
    }

    public Choice(String text, int path) {
        this.text = text;
        this.path = path;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPath() {
        return this.path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public Choice text(String text) {
        this.text = text;
        return this;
    }

    public Choice path(int path) {
        this.path = path;
        return this;
    }
}