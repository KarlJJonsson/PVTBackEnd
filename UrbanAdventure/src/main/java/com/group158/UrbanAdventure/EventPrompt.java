package com.group158.UrbanAdventure;

public class EventPrompt extends Event{
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
}