package com.group158.UrbanAdventure;

public class Choice {
    private String text;
    private int path;

    public Choice(String text, int path) {
        this.text = text;
        this.path = path;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPath() {
        return this.path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public boolean equals(Object obj){
        if(obj instanceof Choice){
            Choice choice = (Choice) obj;
            if(this.getPath() == choice.getPath() && this.getText().equals(choice.getText())){
                return true;
            }
        }
        return false;
    }
}