package com.group158.UrbanAdventure.ModelsUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.group158.UrbanAdventure.Adventure;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;
public class AdventureTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void equalsShouldReturnTrue(){
        //2 different adventures
        Adventure adventure1 = testUtil.generateAdventure();
        Adventure adventure2 = testUtil.generateAnotherAdventure();

        //only id should matter for equal
        adventure2.setId(adventure1.getId());

        assertEquals(adventure1, adventure2, "equal adventures still return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        //identical except id
        Adventure adventure1 = testUtil.generateAdventure();
        Adventure adventure2 = testUtil.generateAdventure();

        assertNotEquals(adventure1, adventure2, "Different id still return equals = true");
    }

    @Test
    public void isIdentical(){
        Adventure adventure1 = testUtil.generateAdventure();
        Adventure adventure2 = testUtil.generateAdventure();

        adventure2.setAdventureTitle("new String");
        assertEquals(false, adventure1.isIdentical(adventure2), " different title returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setAuthor("new String");
        assertEquals(false, adventure1.isIdentical(adventure2), " different author returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setDescriptionText("new String");
        assertEquals(false, adventure1.isIdentical(adventure2), " different description returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setEvents(testUtil.generateEmptyList());
        assertEquals(false, adventure1.isIdentical(adventure2), " different events returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setThumbsDown(7000);
        assertEquals(false, adventure1.isIdentical(adventure2), " different thumbsDown returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setGenre("new String");
        assertEquals(false, adventure1.isIdentical(adventure2), " different genre returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setId("new String");
        assertEquals(false, adventure1.isIdentical(adventure2), " different id returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setLength(7000);
        assertEquals(false, adventure1.isIdentical(adventure2), " different length returns true");
        adventure2 = testUtil.generateAdventure();

        adventure2.setThumbsUp(7000);
        assertEquals(false, adventure1.isIdentical(adventure2), " different thumbsUp returns true");
        adventure2 = testUtil.generateAdventure();
    }

    @Test
    public void getAndSetId(){
        Adventure adventure = new Adventure("", 0, "", "", "", testUtil.generateEmptyList(), 0, 0);
        assertNotNull(adventure.getId(), "getId returns null - broken constructor or getId");

        String newId = "new ID";
        adventure.setId(newId);
        assertEquals(newId, adventure.getId(), "setId doesn't change ID");
    }

    @Test
    public void getAndSetAdventureTitle(){
        String title = "title";
        Adventure adventure = new Adventure(title, 0, "", "", "", testUtil.generateEmptyList(), 0, 0);
        assertEquals(title, adventure.getAdventureTitle(), "getAdventureTitle returns faulty adventureTitle");

        String newTitle = "new title";
        adventure.setAdventureTitle(newTitle);
        assertEquals(newTitle, adventure.getAdventureTitle(), "setAdventureTitle doesn't change AdventureTitle");
    }

    @Test
    public void getAndSetLength(){
        int length = 1;
        Adventure adventure = new Adventure("", length, "", "", "", testUtil.generateEmptyList(), 0, 0);
        assertEquals(length, adventure.getLength(), "getLength returns faulty length");

        int newLength = 2;
        adventure.setLength(newLength);
        assertEquals(newLength, adventure.getLength(), "setLength doesn't change length");
    }
    //Rest of the getters and setters are not tested. Time limitation - other tests are always higher prio.
}