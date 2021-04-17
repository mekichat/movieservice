package com.example.movieservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieServiceController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path="/movie")
    @CrossOrigin(origins ="*")
    List<Movie> getAllMovie(){        
        return movieService.getAll();
    }

    @GetMapping(path="/movie/{id}")
    @CrossOrigin(origins ="*")
    Movie getSingleMovie(@PathVariable Integer id){        
        return movieService.get(id);
    }

    @PostMapping(path="/movie", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Object> addMovie(@RequestBody Movie m){
        return movieService.add(m);
    }

    @PutMapping(path="/movie/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    Movie updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie){
        return movieService.update(id, updatedMovie);
    }

    @PatchMapping(path= "/movie/{id}", consumes = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> partialMovieUpdate(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        return movieService.partialUpdate(id, updatedMovie);
    }

    @DeleteMapping(path="/movie/{id}")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> deleteMovie(@PathVariable Integer id){
        return movieService.delete(id);
    }
    
}
