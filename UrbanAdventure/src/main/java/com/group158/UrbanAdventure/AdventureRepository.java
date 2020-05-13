package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Optional;

import com.group158.UrbanAdventure.Models.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdventureRepository extends MongoRepository<Adventure, String> {
    
    Optional<List<Adventure>> findAllByAdventureTitle(String adventureTitle); //Gör om till findAllAdvnetureTitle
    Optional<Adventure> findById(String Id);
}