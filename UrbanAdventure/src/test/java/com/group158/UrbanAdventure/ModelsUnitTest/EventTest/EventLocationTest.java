package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventLocation;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventLocationTest {

    Testutilities testUtil = new Testutilities();
    
    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventLocation.class), "EventLocation is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventLocation event1 = testUtil.generateEventLocation();
        EventLocation event2 = testUtil.generateEventLocation();

        assertEquals(event1, event2, "equal EventLocation objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventLocation event1 = testUtil.generateEventLocation();
        EventLocation event2 = testUtil.generateEventLocation();
        
        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setCurrentlyActive(!event2.getCurrentlyActive());
        assertNotEquals(event1, event2, "Different currentylActive returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setDespawn(!event2.getDespawn());
        assertNotEquals(event1, event2, "Different despawn returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setTriggered(!event2.getTriggered());
        assertNotEquals(event1, event2, "Different triggered returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setVisible(!event2.getVisible());
        assertNotEquals(event1, event2, "Different visible returns equals = true");

        event2 = testUtil.generateEventLocation();
        event2.setLocation(testUtil.generateAnotherLocation());
        assertNotEquals(event1, event2, "Different Location returns equals = true - could also be caused by Location.equals");

    }

    @Test
    public void getAndSetCurrentlyActive(){
        boolean currentlyActive = true;
        EventLocation event = new EventLocation(1, 1, testUtil.generateLocation(), false, false, false, currentlyActive);
        assertEquals(currentlyActive, event.getCurrentlyActive(), "getCurrentlyActive returns faulty value");

        boolean newCurrentlyActive = false;
        event.setCurrentlyActive(newCurrentlyActive);
        assertEquals(newCurrentlyActive, event.getCurrentlyActive(), "setCurrentlyActive doesn't change value");

    }
}