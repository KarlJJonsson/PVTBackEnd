package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Class for testing equals methods for all of the models. Unnecessary in one way, but was created because of
 * equal method problems in deep hierarchy.
 */

@SpringBootTest
public class ModelsEqualTests {

    Testutilities testUtil = new Testutilities();
    Adventure adventure = testUtil.generateAdventure();

    @Test
    public void testEqualsAdventure(){
        Adventure adv1 = testUtil.generateAdventure();
        adv1.setId("id");
        Adventure adv2 = testUtil.generateAdventure();
        adv2.setId("id");
        assertEquals(adv1, adv2);
    }

    @Test
    public void testEqualsEventMessage(){
        EventMessage msg1 = new EventMessage(1, 2, "asd");
        EventMessage msg2 = new EventMessage(1, 2, "asd");

        assertEquals(msg1, msg2);
        assertEquals(msg1 == msg2, false);
    }

    @Test
    public void testEqualsEventEnd(){
        EventEnd msg3 = new EventEnd(1, 2, "asd");
        EventEnd msg4 = new EventEnd(1, 2, "asd");

        assertEquals(msg3, msg4);
        assertEquals(msg3 == msg4, false);
    }

    @Test
    public void testEqualsEventItemReceive(){
        EventItemReceive msg5 = new EventItemReceive(1, 2, "asd", "dsa");
        EventItemReceive msg6 = new EventItemReceive(1, 2, "asd", "dsa");

        assertEquals(msg5, msg6);
        assertEquals(msg5 == msg6, false);
    }

    @Test
    public void testEqualsEventLast(){
        EventLast msg7 = new EventLast(1, 2, "asd", 3);
        EventLast msg8 = new EventLast(1, 2, "asd", 3);

        assertEquals(msg7, msg8);
        assertEquals(msg7 == msg8, false);
    }

    @Test
    public void testEqualsChoice(){
        Choice choice1 = new Choice("asdasd", 1);
        Choice choice2 = new Choice("asdasd", 1);

        assertEquals(choice1, choice2);
        assertEquals(choice1 == choice2, false);
    }
    
    @Test
    public void testEqualsEventMultipleChoice(){
        Choice choice = new Choice("asdasd", 1);

        List<Choice> choices = new ArrayList<Choice>();
        choices.add(choice);

        EventMultipleChoice msg9 = new EventMultipleChoice(1, 1, choices, "asd");
        EventMultipleChoice msg10 = new EventMultipleChoice(1, 1, choices, "asd");

        assertEquals(msg9, msg10);
        assertEquals(msg9 == msg10, false);
    }

    @Test
    public void testEqualsEventPrompt(){
        EventPrompt msg11 = new EventPrompt(1, 2, "asd", "dsa", "dasf", 3);
        EventPrompt msg12 = new EventPrompt(1, 2, "asd", "dsa", "dasf", 3);

        assertEquals(msg11, msg12);
        assertEquals(msg11 == msg12, false);
    }

    @Test
    public void testEqualsLocation(){
        Location location1 = new Location(1, 2, 3);
        Location location2 = new Location(1, 2, 3);

        assertEquals(location1, location2);
        assertEquals(location1 == location2, false);
    }

    @Test
    public void testEqualsItem(){
        Item item1 = new Item("name");
        Item item2 = new Item("name");

        assertEquals(item1, item2);
        assertEquals(item1 == item2, false);
    }
    
}