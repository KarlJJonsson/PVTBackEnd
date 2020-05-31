package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventStreetLight;
import com.group158.UrbanAdventure.Location;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the models. Some models are deprecated and are therefore not included in the tests. The files are still left in the project though, since we
 * have restructured out models a bit, and removed/brought back classes before. To reassure we won't have to re-write these in case we need to bring them back,
 * we've choosen to keep them in the project.
 */

public class EventStreetLightTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventStreetLight.class), "eventStreetLight is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventStreetLight event = testUtil.generateStreetLight();
        EventStreetLight event2 = testUtil.generateStreetLight();

        assertEquals(event, event2, "equals eventStreetLight objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventStreetLight event1 = testUtil.generateStreetLight();
        EventStreetLight event2 = testUtil.generateStreetLight();

        event2.setDelay(event2.getDelay()+1);
        assertNotEquals(event1, event2, "different delay still return equals = true");

        event2 = testUtil.generateStreetLight();
        event2.setLocation(testUtil.generateAnotherLocation());
        assertNotEquals(event1, event2, "different locations still return equals = true - could also be caused by Location.equals");

        event2 = testUtil.generateStreetLight();
        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index still return equals = true");

        event2 = testUtil.generateStreetLight();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path still return equals = true");
    }

    @Test
    public void getAndSetDelay(){
        int delay = 2;
        EventStreetLight event = new EventStreetLight(1, 1, testUtil.generateLocation(), delay); //create event with delay = 2
        assertEquals(delay, event.getDelay(), "getDelay returns faulty delay");

        int newDelay = 4;
        event.setDelay(newDelay);
        assertEquals(newDelay, event.getDelay(), "setDelay doesn't change delay");
    }

    @Test
    public void getAndSetLocation(){
        Location location = testUtil.generateLocation();
        EventStreetLight event = new EventStreetLight(1, 1, location, 1);
        assertEquals(location, event.getLocation(), "getLocation return faulty location - could also be caused by Location.equals");

        Location newLocation = testUtil.generateAnotherLocation();
        event.setLocation(newLocation);
        assertEquals(newLocation, event.getLocation(), "setLocation doesn't change location - could also be caused by Location.equals");
    }
}