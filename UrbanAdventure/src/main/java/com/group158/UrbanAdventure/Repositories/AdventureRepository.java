package com.group158.UrbanAdventure.Repositories;

import java.util.List;
import java.util.Optional;

import com.group158.UrbanAdventure.Models.Adventure;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdventureRepository extends MongoRepository<Adventure, String> {
    
    Optional<List<Adventure>> findAllByAdventureTitle(String adventureTitle);
    Optional<Adventure> findById(String Id);
}