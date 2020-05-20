package com.group158.UrbanAdventure.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Roles")
public class Role {
    @Id
    private String id;

    private ERole name;

    public Role() {
    }

    public Role(String id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return this.name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}