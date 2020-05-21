package com.group158.UrbanAdventure.User;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository {
    User findByUserName(String email);
}