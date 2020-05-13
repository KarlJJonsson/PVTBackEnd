package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdventureRepository extends MongoRepository<Adventure, String> {
    
    Optional<List<Adventure>> findAllByAdventureTitle(String adventureTitle);
    Optional<Adventure> findById(String Id);
}