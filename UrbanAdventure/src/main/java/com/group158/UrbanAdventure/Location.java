package com.group158.UrbanAdventure;

public class Location{
    private double latitude;
    private double longitude;
    private int radius;

    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
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

    public Location(int radius, double latitude, double longitude){
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Location){
            Location location = (Location) object;
            if(this.latitude == location.latitude
                && this.longitude == location.longitude
                && this.radius == location.radius
            ){
                return true;
            }
        }
        return false;
    }
}