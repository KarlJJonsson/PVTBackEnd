package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventEnd;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventEndTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventEnd.class), "EventEnd is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventEnd event1 = testUtil.generateEnd();
        EventEnd event2 = testUtil.generateEnd();

        assertEquals(event1, event2, "equals EventEnd objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventEnd event1 = testUtil.generateEnd();
        EventEnd event2 = testUtil.generateEnd();

        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "different index still returns equals = true");

        event2 = testUtil.generateEnd();
        event2.setMessage(event2.getMessage()+" new String");
        assertNotEquals(event1, event2, "different message still returns equals = true");

        event2 = testUtil.generateEnd();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "different path still returns equals = true");
    }

    @Test
    public void getAndSetMessage(){
        String msg = "starter String";
        EventEnd event = new EventEnd(1, 1, msg);
        assertEquals(msg, event.getMessage(), "getMessage return faulty message");

        String newMsg = "different String";
        event.setMessage(newMsg);
        assertEquals(newMsg, event.getMessage(), "setMessage doesn't change message");
    }
}