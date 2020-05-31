package com.group158.UrbanAdventure.ModelsUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.StreetLight;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class StreetLightTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void equalsShouldReturnTrue(){
        StreetLight streetLight1 = testUtil.generateStreetLight();
        StreetLight streetLight2 = testUtil.generateStreetLight();

        assertEquals(streetLight1, streetLight2, "equal streetLights still return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        StreetLight streetLight1 = testUtil.generateStreetLight();
        StreetLight streetLight2 = testUtil.generateStreetLight();

        streetLight2.setLatitude(streetLight2.getLatitude()+1);
        assertNotEquals(streetLight1, streetLight2, "Different latitude still returns equals = true");

        streetLight2 = testUtil.generateStreetLight();
        streetLight2.setLongitude(streetLight2.getLongitude()+1);
        assertNotEquals(streetLight1, streetLight2, "Different longitude still returns equals = true");

        streetLight2 = testUtil.generateStreetLight();
        streetLight2.setNumberOfBlinks(streetLight2.getNumberOfBlinks()+1);
        assertNotEquals(streetLight1, streetLight2, "Different numberOfBlinks still returns equals = true");

        streetLight2 = testUtil.generateStreetLight();
        streetLight2.setSecondsInBetween(streetLight2.getSecondsInBetween()+1);
        assertNotEquals(streetLight1, streetLight2, "Different secondsInBetween still returns equals = true");

        streetLight2 = testUtil.generateStreetLight();
        streetLight2.setTimeOn(streetLight2.getTimeOn()+1);
        assertNotEquals(streetLight1, streetLight2, "Different getTimeOn still returns equals = true");
    }

    @Test
    public void getAndSetLatitude(){
        double latitude = 1;
        StreetLight streetLight = new StreetLight(latitude, 0, 0, 0, 0);
        assertEquals(latitude, streetLight.getLatitude(), "getLatitude returns faulty latitude");

        double newLatitude = 2;
        streetLight.setLatitude(newLatitude);
        assertEquals(newLatitude, streetLight.getLatitude(), "setLatitude doesn't change latitude");
    }

    @Test
    public void getAndSetLongitude(){
        double longitude = 1;
        StreetLight streetLight = new StreetLight(0, longitude, 0, 0, 0);
        assertEquals(longitude, streetLight.getLongitude(), "getLongitude returns faulty longitude");

        double newLongitude = 2;
        streetLight.setLongitude(newLongitude);
        assertEquals(newLongitude, streetLight.getLongitude(), "setLongitude doesn't change longitude");
    }

    @Test
    public void getAndSetnumberOfBlinks(){
        int numberOfBlinks = 1;
        StreetLight streetLight = new StreetLight(0, 0, numberOfBlinks, 0, 0);
        assertEquals(numberOfBlinks, streetLight.getNumberOfBlinks(), "getNumberOfBlinks returns faulty numberOfBlinks");

        int newNumberOfBlinks = 2;
        streetLight.setNumberOfBlinks(newNumberOfBlinks);
        assertEquals(newNumberOfBlinks, streetLight.getNumberOfBlinks(), "setNumberOfBlinks doesn't change numberOfBlinks");
    }

    @Test
    public void getAndSetTimeOn(){
        int timeOn = 1;
        StreetLight streetLight = new StreetLight(0, 0, 0, timeOn, 0);
        assertEquals(timeOn, streetLight.getTimeOn(), "getTimeOn returns faulty timeOn");

        int newTimeOn = 2;
        streetLight.setTimeOn(newTimeOn);
        assertEquals(newTimeOn, streetLight.getTimeOn(), "setTimeOn doesn't change timeOn");
    }

    @Test
    public void getAndSetSecondsInBetween(){
        int secondsInBetween = 1;
        StreetLight streetLight = new StreetLight(0, 0, 0, 0, secondsInBetween);
        assertEquals(secondsInBetween, streetLight.getSecondsInBetween(), "getSecondsInBetween returns faulty secondsInBetween");

        int newSecondsInBetween = 2;
        streetLight.setSecondsInBetween(newSecondsInBetween);
        assertEquals(newSecondsInBetween, streetLight.getSecondsInBetween(), "setSecondsInBetween doesn't change secondsInBetween");
    }
    
}