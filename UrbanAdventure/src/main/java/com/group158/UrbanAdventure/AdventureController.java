package com.group158.UrbanAdventure;

import java.util.List;
import java.util.Optional;

import com.group158.UrbanAdventure.Models.*;

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
    AdventureRepository adventureRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Adventure>> getAllStories(){
        List<Adventure> stories = adventureRepository.findAll();
        return new ResponseEntity<List<Adventure>>(stories, HttpStatus.OK);
    }

    @GetMapping("/all/hello")
    public ResponseEntity<String> getHelloMsg(){
        return new ResponseEntity<String>("Hello!", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdventure(@RequestBody Adventure Adventure) {
        adventureRepository.save(Adventure);
        System.out.println(Adventure.getId());
        return new ResponseEntity<String>("Adventure added!", HttpStatus.CREATED);
    }

        //fixa att remove körs på ID och inte title
    // @DeleteMapping("/remove/{adventureTitle}")
    // public ResponseEntity<Adventure> deleteAdventureById(@PathVariable("adventureTitle") String adventureTitle){
    //     Optional<Adventure> Adventure = AdventureRepository.findByAdventureTitle(adventureTitle);
    //     if(Adventure.isPresent()){
    //         AdventureRepository.delete(Adventure.get());
    //         return new ResponseEntity<Adventure>(Adventure.get(), HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    //Returna List istället för Adventure
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

    //GetMapping för id, för att hitta unika äventyr
}