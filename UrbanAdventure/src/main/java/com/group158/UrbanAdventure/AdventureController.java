package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")

public class AdventureController {
    
    @Autowired
    AdventureRepository AdventureRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Adventure>> getAllStories(){
        List<Adventure> stories = AdventureRepository.findAll();
        return new ResponseEntity<List<Adventure>>(stories, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdventure(@RequestBody Adventure Adventure) {
        AdventureRepository.save(Adventure);
        System.out.println(Adventure.getId());
        return new ResponseEntity<String>("Adventure added!", HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{adventureTitle}")
    public ResponseEntity<Adventure> deleteAdventureById(@PathVariable("adventureTitle") String adventureTitle){
        Optional<Adventure> Adventure = AdventureRepository.findByAdventureTitle(adventureTitle);
        if(Adventure.isPresent()){
            AdventureRepository.delete(Adventure.get());
            return new ResponseEntity<Adventure>(Adventure.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{adventureTitle}")
    public ResponseEntity<Adventure> getByAdventureTitle(@PathVariable("adventureTitle") String adventureTitle){
        Optional<Adventure> Adventure = AdventureRepository.findByAdventureTitle(adventureTitle);
        if(Adventure.isPresent()){
            return new ResponseEntity<Adventure>(Adventure.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @GetMapping("/find/byId/{id}")
    // public ResponseEntity<Adventure> getById(@PathVariable("id") String id){
    //     Optional<Adventure> Adventure = AdventureRepository.findById(id);
    //     if(Adventure.isPresent()){
    //         return new ResponseEntity<Adventure>(Adventure.get(), HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    // @GetMapping("/search/{title}/{EventId}")
    // public ResponseEntity<Event> getEventByTitle(@PathVariable("title") String title, @PathVariable("EventId") int eventId){
    //     Optional<Adventure> Adventure = AdventureRepository.findByAdventureTitle(title);
    //     if(Adventure.isPresent() && Adventure.get().getEvents().size() > eventIndex){
    //         Event event = Adventure.get().getEvent(eventIndex);
    //         return new ResponseEntity<Event>(event, HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    // @GetMapping("/find/{id}/{EventIndex}")
    // public ResponseEntity<Event> getEventById(@PathVariable("id") String id, @PathVariable("EventIndex") int eventIndex){
    //     Optional<Adventure> Adventure = AdventureRepository.findById(id);
    //     if(Adventure.isPresent()){
    //         Event event = Adventure.get().getEvent(eventIndex);
    //         return new ResponseEntity<Event>(event, HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}