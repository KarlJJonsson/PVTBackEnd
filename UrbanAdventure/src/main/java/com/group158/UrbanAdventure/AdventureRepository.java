package com.group158.UrbanAdventure;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdventureRepository extends MongoRepository<Adventure, String> {

    Optional<Adventure> findByAdventureTitle(String adventureTitle);
    Optional<Adventure> findById(String Id);
}