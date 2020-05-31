package com.group158.UrbanAdventure.ModelsUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group158.UrbanAdventure.Choice;
import com.group158.UrbanAdventure.Testutilities;

import org.junit.jupiter.api.Test;

public class ChoiceTest {

    Testutilities testUtil = new Testutilities();

    @Test
    public void equalsShouldReturnTrue(){
        Choice choice1 = testUtil.generateChoice();
        Choice choice2 = testUtil.generateChoice();

        assertEquals(choice1, choice2, "equal choices still return equals = false");
    }

    @Test
    public void equalsShouldReturnFalse(){
        Choice choice1 = testUtil.generateChoice();
        Choice choice2 = testUtil.generateChoice();

        choice2.setPath(choice2.getPath()+1);
        assertNotEquals(choice1, choice2, "Different paths still return equals = true");

        choice2 = testUtil.generateChoice();
        choice2.setText(choice2.getText()+" new String");
        assertNotEquals(choice1, choice2, "Different text still returns equals = true");
    }

    @Test
    public void getAndSetPath(){
        int path = 1;
        Choice choice = new Choice("", path);
        assertEquals(path, choice.getPath(), "getPath returns faulty path");

        int newPath = 2;
        choice.setPath(newPath);
        assertEquals(newPath, choice.getPath(), "setPath doesn't change path");
    }

    @Test
    public void getAndSetText(){
        String text = "text";
        Choice choice = new Choice(text, 1);
        assertEquals(text, choice.getText(), "getText return faulty text");

        String newText = "new Text";
        choice.setText(newText);
        assertEquals(newText, choice.getText(), "setText doesn't change Text");
    }
}