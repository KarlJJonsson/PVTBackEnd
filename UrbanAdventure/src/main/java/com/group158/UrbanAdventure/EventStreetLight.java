package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Objects;

public class EventStreetLight extends Event {
    private List<StreetLight> streetLights;

    public EventStreetLight(int index, int path, List<StreetLight> streetLights) {
        super(index, path);
        this.streetLights = streetLights;
    }

    public List<StreetLight> getStreetLights() {
        return this.streetLights;
    }

    public void setStreetLights(List<StreetLight> streetLights) {
        this.streetLights = streetLights;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventStreetLight)) {
            return false;
        }
        EventStreetLight eventStreetLight = (EventStreetLight) o;
        return (Objects.equals(streetLights, eventStreetLight.getStreetLights())
            && this.getIndex() == eventStreetLight.getIndex()
            && this.getPath() == eventStreetLight.getPath()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(streetLights);
    }
}