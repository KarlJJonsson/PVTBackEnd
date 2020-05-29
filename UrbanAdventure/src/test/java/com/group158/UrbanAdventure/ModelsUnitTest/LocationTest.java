package com.group158.UrbanAdventure.ModelsUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Location;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class LocationTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void equalsShouldReturnTrue(){
        Location location1 = testUtil.generateLocation();
        Location location2 = testUtil.generateLocation();

        assertEquals(location1, location2, "equal locations still return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        Location location1 = testUtil.generateLocation();
        Location location2 = testUtil.generateLocation();

        location2.setLatitude(location2.getLatitude()+1);

        assertNotEquals(location1, location2, "Different latitude still return equals = true");

        location2 = testUtil.generateLocation();

        location2.setLongitude(location2.getLongitude()+1);

        assertNotEquals(location2, location1, "Different longitude still returns equals = true");

        location2 = testUtil.generateLocation();

        location2.setRadius(location2.getRadius()+1);

        assertNotEquals(location2, location1, "Different radius still returns equals = true");
    }

    @Test
    public void getAndSetLatitude(){
        double latitude = 40.5;

        Location location = new Location(1, latitude, 1);

        assertEquals(latitude, location.getLatitude(), "getLatitude returns faulty latitude");

        double newLatitude = 50.5;

        location.setLatitude(newLatitude);

        assertEquals(newLatitude, location.getLatitude(), "setLatitude doesn't change the latitude");
    }

    @Test 
    public void getAndSetLongitude(){
        double longitude = 40.5;

        Location location = new Location(1, 1, longitude);

        assertEquals(longitude, location.getLongitude(), "getLongitude returns faulty longitude");

        double newLongitude = 50.5;

        location.setLongitude(newLongitude);

        assertEquals(newLongitude, location.getLongitude(), "setLongitude doesn't change the longitude");

    }
    
    @Test
    public void getAndSetRadius(){
        int radius = 4;

        Location location = new Location(radius, 1, 1);

        assertEquals(radius, location.getRadius(), "getRadius returns faulty radius");

        int newRadius = 7;

        location.setRadius(newRadius);

        assertEquals(newRadius, location.getRadius(), "setRadius doesn't change the radius");

    }
}