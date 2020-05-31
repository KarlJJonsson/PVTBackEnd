package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventMessage;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventMessageTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventMessage.class), "EventMessage is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventMessage event1 = testUtil.generateMessage();
        EventMessage event2 = testUtil.generateMessage();

        assertEquals(event1, event2, "equals EventMessage objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventMessage event1 = testUtil.generateMessage();
        EventMessage event2 = testUtil.generateMessage();

        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "different index still returns equals = true");

        event2 = testUtil.generateMessage();
        event2.setMessage(event2.getMessage()+" new String");
        assertNotEquals(event1, event2, "different message still returns equals = true");

        event2 = testUtil.generateMessage();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "different path still returns equals = true");
    }

    @Test
    public void getAndSetMessage(){
        String msg = "starter String";
        EventMessage event = new EventMessage(1, 1, msg);
        assertEquals(msg, event.getMessage(), "getMessage return faulty message");

        String newMsg = "different String";
        event.setMessage(newMsg);
        assertEquals(newMsg, event.getMessage(), "setMessage doesn't change message");
    }
}