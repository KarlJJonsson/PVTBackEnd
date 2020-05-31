package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventItemReceive;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventItemReceiveTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventItemReceive.class), "EventItemReceive is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventItemReceive event1 = testUtil.generateItemReceive();
        EventItemReceive event2 = testUtil.generateItemReceive();

        assertEquals(event1, event2, "equal EventItemReceive objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventItemReceive event1 = testUtil.generateItemReceive();
        EventItemReceive event2 = testUtil.generateItemReceive();
        
        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index still return equals = true");

        event2 = testUtil.generateItemReceive();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path still return equals = true");

        event2 = testUtil.generateItemReceive();
        event2.setMessage(event2.getMessage()+" new message");
        assertNotEquals(event1, event2, "Different message still return equals = true");

        event2 = testUtil.generateItemReceive();
        event2.setItem(event2.getItem()+" new item");
        assertNotEquals(event1, event2, "Different item still return equals = true");
    }


    @Test
    public void getAndSetMessage(){
        String msg = "starter String";
        EventItemReceive event = new EventItemReceive(1, 1, msg, "");
        assertEquals(msg, event.getMessage(), "getMessage return faulty message");

        String newMsg = "different String";
        event.setMessage(newMsg);
        assertEquals(newMsg, event.getMessage(), "setMessage doesn't change message");
    }

    @Test
    public void getAndSetItem(){
        String item = "starter String";
        EventItemReceive event = new EventItemReceive(1, 1, "", item);
        assertEquals(item, event.getItem(), "getItem return faulty item");

        String newItem = "different String";
        event.setItem(newItem);
        assertEquals(newItem, event.getItem(), "setItem doesn't change Item");

    }
}