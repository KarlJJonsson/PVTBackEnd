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

    public int getNumberOfParts() { return this.numberOfParts; }

    public void setNumberOfParts(int numberOfParts) { this.numberOfParts = numberOfParts; }

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

    @Override
    public boolean equals(Object object){
        if (object instanceof Adventure){
            Adventure adventure = (Adventure) object;
            if(this.id.equals(adventure.id)
                && this.adventureTitle.equals(adventure.adventureTitle)
                && this.length == adventure.length
                && this.genre.equals(adventure.genre)
                && this.numberOfParts == adventure.numberOfParts
                && this.descriptionText.equals(adventure.descriptionText)
                && this.author.equals(adventure.author)
                && this.parts.equals(adventure.parts)
            ){
                return true;
            }
        }
        return false;

    }
}