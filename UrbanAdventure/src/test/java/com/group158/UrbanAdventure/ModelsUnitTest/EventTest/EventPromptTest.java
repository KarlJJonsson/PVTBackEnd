package com.group158.UrbanAdventure.ModelsUnitTest.EventTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Event;
import com.group158.UrbanAdventure.EventPrompt;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class EventPromptTest {
    Testutilities testUtil = new Testutilities();

    @Test
    public void shouldExtendEvent(){
        assertEquals(true, Event.class.isAssignableFrom(EventPrompt.class), "EventPrompt is not a subclass to Event");
    }

    @Test
    public void equalsShouldReturnTrue(){
        EventPrompt event1 = testUtil.generatePrompt();
        EventPrompt event2 = testUtil.generatePrompt();

        assertEquals(event1, event2, "equal EventPrompt objects return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        EventPrompt event1 = testUtil.generatePrompt();
        EventPrompt event2 = testUtil.generatePrompt();

        event2.setIndex(event2.getIndex()+1);
        assertNotEquals(event1, event2, "Different index returns equals = true");

        event2 = testUtil.generatePrompt();
        event2.setPath(event2.getPath()+1);
        assertNotEquals(event1, event2, "Different path returns equals = true");

        event2 = testUtil.generatePrompt();
        event2.setWrongAnswerPath(event2.getWrongAnswerPath()+1);
        assertNotEquals(event1, event2, "Different wrongAnswerPath returns equals = true");

        event2 = testUtil.generatePrompt();
        event2.setMessage(event2.getMessage()+" new message");
        assertNotEquals(event1, event2, "Different message returns equals = true");

        event2 = testUtil.generatePrompt();
        event2.setPromptMessage(event2.getPromptMessage()+" new message");
        assertNotEquals(event1, event2, "Different promptMessage returns equals = true");

        event2 = testUtil.generatePrompt();
        event2.setCorrectAnswer(event2.getCorrectAnswer()+" new message");
        assertNotEquals(event1, event2, "Different correctAnswer returns equals = true");
    }


    @Test
    public void getAndSetMessage(){
        String message = "message";
        EventPrompt event = new EventPrompt(1, 1, message, "", "", 1);
        assertEquals(message, event.getMessage(), "getMessage return faulty message");

        String newMessage = "new message";
        event.setMessage(newMessage);
        assertEquals(newMessage, event.getMessage(), "setMessage doesn't change message");
    }

    @Test
    public void getAndSetWrongAnswerPath(){
        int wrongAnswerPath = 1;
        EventPrompt event = new EventPrompt(1, 1, "", "", "", wrongAnswerPath);
        assertEquals(wrongAnswerPath, event.getWrongAnswerPath(), "getWrongAnswerPath returns faulty wrongAnswerPath");

        int newWrongAnswerPath = 2;
        event.setWrongAnswerPath(newWrongAnswerPath);
        assertEquals(newWrongAnswerPath, event.getWrongAnswerPath(), "setWrongAnswerPath doesn't change wrongAnswerPath");
    }

    @Test
    public void getAndSetPromptMessage(){
        String promptMessage = "promptMessage";
        EventPrompt event = new EventPrompt(1, 1, "", promptMessage, "", 1);
        assertEquals(promptMessage, event.getPromptMessage(), "getPromptMessage return faulty promptMessage");

        String newPromptMessage = "newPromptMessage";
        event.setPromptMessage(newPromptMessage);
        assertEquals(newPromptMessage, event.getPromptMessage(), "setPromptMessage doesn't change promptMessage");
    }

    @Test
    public void getAndSetCorrectAnswer(){
        String correctAnswer = "CorrectAnswer";
        EventPrompt event = new EventPrompt(1, 1, "", "", correctAnswer, 1);
        assertEquals(correctAnswer, event.getCorrectAnswer(), "getCorrectAnswer return faulty correctAnswer");

        String newCorrectAnswer = "newCorrectAnswer";
        event.setCorrectAnswer(newCorrectAnswer);
        assertEquals(newCorrectAnswer, event.getCorrectAnswer(), "setCorrectAnswer doesn't change correctAnswer");
    }

}