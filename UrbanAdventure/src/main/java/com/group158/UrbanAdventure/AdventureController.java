package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")

public class AdventureController {
    
    @Autowired
    AdventureRepository adventureRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Adventure>> getAllAdventures(){
        List<Adventure> adventure = adventureRepository.findAll();
        return new ResponseEntity<List<Adventure>>(adventure, HttpStatus.OK);
    }

    //For testing connections
    @GetMapping("/all/hello")
    public ResponseEntity<String> getHelloMsg(){
        return new ResponseEntity<String>("Hello!", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdventure(@RequestBody Adventure adventure) {
        adventureRepository.save(adventure);
        return new ResponseEntity<String>(adventure.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Adventure> deleteAdventureById(@PathVariable("id") String id){
        Optional<Adventure> adventure = adventureRepository.findById(id);
        if(adventure.isPresent()){
            adventureRepository.delete(adventure.get());
            return new ResponseEntity<Adventure>(adventure.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{adventureTitle}")
    public ResponseEntity<List<Adventure>> getAllByAdventureTitle(@PathVariable("adventureTitle") String adventureTitle){
        Optional<List<Adventure>> adventure = adventureRepository.findAllByAdventureTitle(adventureTitle);
        if(adventure.isPresent()){
            return new ResponseEntity<List<Adventure>>(adventure.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Adventure> getAdventureById(@PathVariable("id") String id){
        Optional<Adventure> adventure = adventureRepository.findById(id);
        if(adventure.isPresent()){
            return new ResponseEntity<Adventure>(adventure.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("update/{id}/rating")
    public ResponseEntity<String> updateAdventureRating(@PathVariable("id") String id, @RequestBody Map<String, Integer> rating){
        Optional<Adventure> searchResult = adventureRepository.findById(id);
        if(searchResult.isPresent()){
            Adventure adventure = searchResult.get();
            adventure.setThumbsDown(rating.get("downvote"));
            adventure.setThumbsUp(rating.get("upvote"));
            adventureRepository.save(adventure);
            return new ResponseEntity<String>("Rating updated!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Adventure doesnt exist", HttpStatus.NOT_FOUND);
        }
    }
}