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
    private int numberOfParts;
    private String descriptionText;
    private String author;
    private List<AdventurePart> parts;
    //private String type;

    //public String getType() { return this.type; }

    //public void setType(String type) {this.type = type; }

    public String getId() {
        return this.id;
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

    public int getNumberOfLocations() { return this.numberOfLocations; }

    // Behöver vi verkligen en setter för numberOfLocations?
    public void setNumberOfLocations(int numberOfLocations) { this.numberOfLocations = numberOfLocations; }

    public String getDescriptionText(){ return this.descriptionText; }

    public void setDescriptionText(String descriptionText) { this.descriptionText = descriptionText; }

    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }

    public List<AdventurePart> getParts() {
        return this.parts;
    }

    public void setParts(List<AdventurePart> parts) {
        this.parts = parts;
    }

    // Tog bort type ur constructorn, men kommenterade bara bort bland variabeln och setter och getter
    public Adventure(String adventureTitle, int length, String genre, int numberOfParts, String descriptionText, String author, List<AdventurePart> parts){
        this.id = UUID.randomUUID().toString(); //Genereras med UUID, dvs en universal unique pseudo random generator
        this.adventureTitle = adventureTitle;
        this.length = length;
        this.genre = genre;
        this.numberOfParts = numberOfParts;
        this.descriptionText = descriptionText;
        this.author = author;
        this.parts = parts;

    }

}