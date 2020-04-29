package com.group158.UrbanAdventure;

import java.util.List;

public class Location extends Event {
    
    //Marker LatLong? x och y.

    private double radius;

    public void setRadius(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public Location(double radius, List<Event> children){
        super(children);
        this.radius = radius;
    }
}