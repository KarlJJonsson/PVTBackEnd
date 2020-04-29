package com.group158.UrbanAdventure;

import java.util.List;

public class Message extends Event {
    String text;

    //textSize
    //textColor
    //TextFont

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public Message(String text, List<Event> children){
        super(children);
        this.text = text;
    }
}