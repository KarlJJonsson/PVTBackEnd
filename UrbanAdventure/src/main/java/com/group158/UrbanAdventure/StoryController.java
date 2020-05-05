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
@RequestMapping("/")

public class StoryController {
    
    @Autowired
    StoryRepository storyRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Story>> getAllStories(){
        List<Story> stories = storyRepository.findAll();
        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }

    @PostMapping("/newStory")
    public ResponseEntity<String> createStory(@RequestBody Story story) {
        storyRepository.save(story);
        System.out.println(story.getId());
        return new ResponseEntity<String>("Story added!", HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Story> deleteStoryById(@PathVariable("id") String id){
        Optional<Story> story = storyRepository.findById(id);
        if(story.isPresent()){
            storyRepository.delete(story.get());
            return new ResponseEntity<Story>(story.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/byTitle/{title}")
    public ResponseEntity<Story> getByTitle(@PathVariable("title") String title){
        Optional<Story> story = storyRepository.findByTitle(title);
        if(story.isPresent()){
            return new ResponseEntity<Story>(story.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/byId/{id}")
    public ResponseEntity<Story> getById(@PathVariable("id") String id){
        Optional<Story> story = storyRepository.findById(id);
        if(story.isPresent()){
            return new ResponseEntity<Story>(story.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @GetMapping("/find/{title}/{EventIndex}")
    // public ResponseEntity<Event> getEventByTitle(@PathVariable("title") String title, @PathVariable("EventIndex") int eventIndex){
    //     Optional<Story> story = storyRepository.findByTitle(title);
    //     if(story.isPresent() && story.get().getEvents().size() > eventIndex){
    //         Event event = story.get().getEvent(eventIndex);
    //         return new ResponseEntity<Event>(event, HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    @GetMapping("/find/{id}/{EventIndex}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") String id, @PathVariable("EventIndex") int eventIndex){
        Optional<Story> story = storyRepository.findById(id);
        if(story.isPresent()){
            Event event = story.get().getEvent(eventIndex);
            return new ResponseEntity<Event>(event, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}