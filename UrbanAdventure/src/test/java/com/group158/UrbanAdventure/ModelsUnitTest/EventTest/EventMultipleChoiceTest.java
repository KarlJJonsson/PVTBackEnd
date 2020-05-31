package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import com.group158.UrbanAdventure.Choice;
import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventMultipleChoice;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventMultipleChoiceTest {
    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventMultipleChoice.class), "EventMultipleChoice is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventMultipleChoice event1 = testUtil.generateEventMultipleChoice();
        EventMultipleChoice event2 = testUtil.generateEventMultipleChoice();

        assertEquals(event1, event2, "equal EventMultipleChoice objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventMultipleChoice event1 = testUtil.generateEventMultipleChoice();
        EventMultipleChoice event2 = testUtil.generateEventMultipleChoice();

        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index returns equals = true");

        event2 = testUtil.generateEventMultipleChoice();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path returns equals = true");
        
        event2 = testUtil.generateEventMultipleChoice();
        event2.setMessage(event2.getMessage()+" new message");
        assertNotEquals(event1, event2, "Different message returns equals = true");

        event2 = testUtil.generateEventMultipleChoice();
        event2.setChoices(testUtil.generateListOfChoices());
        assertNotEquals(event1, event2, "Different Choices returns equals = true");
    }


    @Test
    public void getAndSetMessage(){
        String message = "message";
        EventMultipleChoice event = new EventMultipleChoice(1, 1, testUtil.generateEmptyListOfChoices(), message);
        assertEquals(message, event.getMessage(), "getMessage returns faulty message");

        String newMessage = "newMessage";
        event.setMessage(newMessage);
        assertEquals(newMessage, event.getMessage(), "setMessage doesn't change message");
    }

    @Test
    public void getAndSetChoices(){
        List<Choice> choices = testUtil.generateListOfChoices();
        EventMultipleChoice event = new EventMultipleChoice(1, 1, choices, "");
        assertEquals(choices, event.getChoices(), "getChoices returns faulty choices");

        List<Choice> newChoices = testUtil.generateEmptyListOfChoices();
        event.setChoices(newChoices);
        assertEquals(newChoices, event.getChoices(), "setChoices doesn't change choices");
    }
    
}