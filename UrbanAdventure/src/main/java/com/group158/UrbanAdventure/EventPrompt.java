package com.group158.UrbanAdventure;

import java.util.Objects;

public class EventPrompt extends Event {
    private String message;
    private String promptMessage;
    private String correctAnswer;
    private int wrongAnswerPath;

    public String getPromptMessage() {
        return this.promptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswerPath() {
        return this.wrongAnswerPath;
    }

    public void setWrongAnswerPath(int wrongAnswerPath) {
        this.wrongAnswerPath = wrongAnswerPath;
    }


    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public EventPrompt(int index, int path, String message, String promptMessage, String correctAnswer, int wrongAnswerPath){
        super(index, path);
        this.message = message;
        this.promptMessage = promptMessage;
        this.wrongAnswerPath = wrongAnswerPath;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventPrompt)) {
            return false;
        }
        EventPrompt event = (EventPrompt) o;
        return this.getIndex() == event.getIndex() 
        && this.getPath() == event.getPath()
        && this.message.equals(event.getMessage())
        && Objects.equals(promptMessage, event.getPromptMessage())
        && Objects.equals(correctAnswer, event.getCorrectAnswer())
        && Objects.equals(wrongAnswerPath, event.getWrongAnswerPath());
    }
}