package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventMedia;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventMediaTest {
    Testutilities testUtil = new Testutilities();

	@Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventMedia.class), "EventMedia is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventMedia event1 = testUtil.generateMedia();
        EventMedia event2 = testUtil.generateMedia();

        assertEquals(event1, event2, "equal EventMedia objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventMedia event1 = testUtil.generateMedia();
        EventMedia event2 = testUtil.generateMedia();

        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index returns equals = true");

        event2 = testUtil.generateMedia();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path returns equals = true");

        event2 = testUtil.generateMedia();
        event2.setImage(event2.getImage()+" new String");
        assertNotEquals(event1, event2, "Different ImageUrls returns equals = true");

        event2 = testUtil.generateMedia();
        event2.setImage(event2.getSound()+" new String");
        assertNotEquals(event1, event2, "Different soundUrls returns equals = true");

        event2 = testUtil.generateMedia();
        event2.setImage(event2.getVideo()+" new String");
        assertNotEquals(event1, event2, "Different videoUrls returns equals = true");
    }


    @Test
    public void getAndSetImage(){
        String image = "ImageUrl";
        EventMedia event = new EventMedia(1, 1, image, "", "");
        assertEquals(image, event.getImage(), "getImage returns faulty image");

        String newImage = "NewImageUrl";
        event.setImage(newImage);
        assertEquals(newImage, event.getImage(), "setImage doesn't change image");
    }

    @Test
    public void getAndSetSound(){
        String sound = "SoundUrl";
        EventMedia event = new EventMedia(1, 1, "", sound, "");
        assertEquals(sound, event.getSound(), "getSound returns faulty Sound");

        String newSound = "NewSoundUrl";
        event.setSound(newSound);
        assertEquals(newSound, event.getSound(), "setSound doesn't change Sound");
    }

    @Test
    public void getAndSetVideo(){
        String video = "VideoUrl";
        EventMedia event = new EventMedia(1, 1, "", "", video);
        assertEquals(video, event.getVideo(), "getVideo returns faulty video");

        String newVideo = "NewVideoUrl";
        event.setVideo(newVideo);
        assertEquals(newVideo, event.getVideo(), "setVideo doesn't change video");
    }
    
}