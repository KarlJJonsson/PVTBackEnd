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
    private List<AdventurePart> parts;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<AdventurePart> getParts() {
        return this.parts;
    }

    public void setParts(List<AdventurePart> parts) {
        this.parts = parts;
    }

    public Adventure(String title, int length, List<AdventurePart> parts, String type){
        this.id = UUID.randomUUID().toString(); //Genereras med UUID, dvs en universal unique pseudo random generator
        this.adventureTitle = title;
        this.length = length;
        this.parts = parts;
        this.type = type;
    }

}