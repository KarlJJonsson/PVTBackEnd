package com.group158.UrbanAdventure;

public class Location{
    private double latitude;
    private double longitude;
    private double radius;

    public void setRadius(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public Location(double radius, double latitude, double longitude){
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}