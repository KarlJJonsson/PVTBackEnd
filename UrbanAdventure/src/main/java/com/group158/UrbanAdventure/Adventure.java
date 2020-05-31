package com.group158.UrbanAdventure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Adventures")
public class Adventure {
    private String id; // tog bort annoteringen @id, den försökte göra om string till int, vet ej orsak
                       // men någonting under the hood som bråkade
    private String adventureTitle;
    private int length;
    private String genre;
    private String descriptionText;
    private String author;
    private List<Event> events;

    private int thumbsUp;
    private int thumbsDown;

    public int getThumbsDown() {
        return this.thumbsDown;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public int getThumbsUp() {
        return this.thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdventureTitle() {
        return this.adventureTitle;
    }

    public void setAdventureTitle(String adventureTitle) {
        this.adventureTitle = adventureTitle;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {return this.genre;}

    public void setGenre(String genre) { this.genre = genre; }

    public String getDescriptionText(){ return this.descriptionText; }

    public void setDescriptionText(String descriptionText) { this.descriptionText = descriptionText; }

    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Adventure(String adventureTitle, int length, String genre, String descriptionText, String author, List<Event> events, int thumbsUp, int thumbsDown){
        this.id = UUID.randomUUID().toString(); //Genereras med UUID, dvs en universal unique pseudo random generator
        this.adventureTitle = adventureTitle;
        this.length = length;
        this.genre = genre;
        this.descriptionText = descriptionText;
        this.author = author;
        this.events = events;
        this.thumbsUp = thumbsUp;
        this.thumbsDown = thumbsDown;
    }

    public boolean isIdentical(Adventure adventure){
        return (this.id.equals(adventure.id)
        && this.adventureTitle.equals(adventure.adventureTitle)
        && this.length == adventure.length
        && this.genre.equals(adventure.genre)
        && this.descriptionText.equals(adventure.descriptionText)
        && this.author.equals(adventure.author)
        && this.events.equals(adventure.events)
        && this.thumbsUp == adventure.thumbsUp
        && this.thumbsDown == adventure.thumbsDown);
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Adventure){
            Adventure adventure = (Adventure) object;
            return this.id.equals(adventure.id);
        }
        else{
            return false;
        }
    }
}