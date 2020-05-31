package com.group158.UrbanAdventure;

import java.util.Objects;

public class EventMedia extends Event {
    private String image;
    private String sound;
    private String video;

    public EventMedia(int path, int index, String image, String sound, String video) {
        super(index, path);
        this.image = image;
        this.sound = sound;
        this.video = video;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventMedia)) {
            return false;
        }
        EventMedia eventMedia = (EventMedia) o;
        return (Objects.equals(image, eventMedia.image) 
            && Objects.equals(sound, eventMedia.sound) 
            && Objects.equals(video, eventMedia.video)
            && this.getIndex() == eventMedia.getIndex()
            && this.getPath() == eventMedia.getPath()
        );
    }
}