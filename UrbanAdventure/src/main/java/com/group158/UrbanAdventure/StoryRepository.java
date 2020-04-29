package com.group158.UrbanAdventure;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {

    Optional<Story> findById(String id);
    Optional<Story> findByTitle(String title);
}