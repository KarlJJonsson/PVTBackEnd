package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventMessage;

import org.junit.jupiter.api.Test;

/**
 * These tests implements EventMessage since Event is an abstracts class. Variables tested here are inherited in all subclasses to Event.
 */
public class EventTest {

    @Test
    public void getAndSetIndex(){
        int index = 1;
        Event event = new EventMessage(index, 0, "");
        assertEquals(index, event.getIndex(), "getIndex returns faulty index");

        int newIndex = 2;
        event.setIndex(newIndex);
        assertEquals(newIndex, event.getIndex(), "setIndex doesn't change index");
    }

    @Test
    public void getAndSetPath(){
        int path = 1;
        Event event = new EventMessage(0, path, "");
        assertEquals(path, event.getPath(), "getPath returns faulty path");

        int newPath = 2;
        event.setPath(newPath);
        assertEquals(newPath, event.getPath(), "setPath doesn't change path");
    }
    
}