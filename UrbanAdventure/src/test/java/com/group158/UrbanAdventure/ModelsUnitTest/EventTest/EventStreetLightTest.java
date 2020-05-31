package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventStreetLight;
import com.group158.UrbanAdventure.StreetLight;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventStreetLightTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventStreetLight.class), "eventStreetLight is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventStreetLight event1 = testUtil.generateEventStreetLight();
        EventStreetLight event2 = testUtil.generateEventStreetLight();

        assertEquals(event1, event2, "equal eventStreetLight objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventStreetLight event1 = testUtil.generateEventStreetLight();
        EventStreetLight event2 = testUtil.generateEventStreetLight();

        event2.setStreetLights(testUtil.generateAnotherListOfStreetLights());
        assertNotEquals(event1, event2, "different streetLights still return equals = true");

        event2 = testUtil.generateEventStreetLight();
        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index still return equals = true");

        event2 = testUtil.generateEventStreetLight();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path still return equals = true");
    }

    @Test
    public void getAndSetStreetLights(){
        List<StreetLight> streetLights = testUtil.generateListOfStreetLights();
        EventStreetLight event = new EventStreetLight(1, 2, streetLights);
        assertEquals(streetLights, event.getStreetLights(), "getStreetLights returns faulty delay");

        List<StreetLight> newStreetLights = testUtil.generateAnotherListOfStreetLights();
        event.setStreetLights(newStreetLights);
        assertEquals(newStreetLights, event.getStreetLights(), "setStreetLights doesn't change delay");
    }
}