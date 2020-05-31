package com.group158.UrbanAdventure;

import java.util.Objects;

public class StreetLight {
    private double latitude;
    private double longitude;
    private int numberOfBlinks;
    private int timeOn;
    private int secondsInBetween;

    public StreetLight(double latitude, double longitude, int numberOfBlinks, int timeOn, int secondsInBetween) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberOfBlinks = numberOfBlinks;
        this.timeOn = timeOn;
        this.secondsInBetween = secondsInBetween;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getNumberOfBlinks() {
        return this.numberOfBlinks;
    }

    public void setNumberOfBlinks(int numberOfBlinks) {
        this.numberOfBlinks = numberOfBlinks;
    }

    public int getTimeOn() {
        return this.timeOn;
    }

    public void setTimeOn(int timeOn) {
        this.timeOn = timeOn;
    }

    public int getSecondsInBetween() {
        return this.secondsInBetween;
    }

    public void setSecondsInBetween(int secondsInBetween) {
        this.secondsInBetween = secondsInBetween;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StreetLight)) {
            return false;
        }
        StreetLight streetLight = (StreetLight) o;
        return latitude == streetLight.latitude && longitude == streetLight.longitude && numberOfBlinks == streetLight.numberOfBlinks && timeOn == streetLight.timeOn && secondsInBetween == streetLight.secondsInBetween;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, numberOfBlinks, timeOn, secondsInBetween);
    }
}