package com.example.movieservice;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MovieServiceController {

    @Autowired
    private MovieServiceRepository movieRepository;

    @GetMapping(path="/movie")
    @CrossOrigin(origins ="*")
    List<Movie> getAllMovie(){
        var movie = new ArrayList<Movie>();
        for(Movie m1 : movieRepository.findAll()){
            movie.add(m1);
        }
        return movie;
    }

    @GetMapping(path="/movie/{id}")
    @CrossOrigin(origins ="*")
    Movie getSingleMovie(@PathVariable Integer id){        
        return movieRepository.findById(id).get();
    }

    @PostMapping(path="/movie", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Object> addMovie(@RequestBody Movie m){
        movieRepository.save(m);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(m.getId())
        .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(path="/movie/{id}", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins ="*")
    Movie updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie){
        Movie dbMovie = movieRepository.findById(id).get();
        dbMovie.setTitle(updatedMovie.getTitle());
        dbMovie.setDirector(updatedMovie.getDirector());
        dbMovie.setActor(updatedMovie.getActor());
        dbMovie.setGenre(updatedMovie.getGenre());
        dbMovie.setReleased(updatedMovie.getReleased());
        dbMovie.setLanguage(updatedMovie.getLanguage());

        movieRepository.save(dbMovie);       

        return dbMovie;

    }

    @PatchMapping(path= "/movie/{id}", consumes = "application/json")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> partialMovieUpdate(@PathVariable Integer id, @RequestBody Movie updatedMovie) {

        Movie dbMovie = movieRepository.findById(id).get();

        try {
            if(updatedMovie.getTitle() != null){
                dbMovie.setTitle(updatedMovie.getTitle());
    
            }
            if(updatedMovie.getReleased()!= 0){ 
                dbMovie.setReleased(updatedMovie.getReleased());
    
            }
            if(updatedMovie.getDirector()!= null){ 
                dbMovie.setDirector(updatedMovie.getDirector());
    
            }
            if(updatedMovie.getGenre() != null){
                dbMovie.setGenre(updatedMovie.getGenre());
    
            }
            if(updatedMovie.getActor() != null){
                dbMovie.setActor(updatedMovie.getActor());
    
            }
            if(updatedMovie.getLanguage() != null){
                dbMovie.setLanguage(updatedMovie.getLanguage());
    
            } 
            
            movieRepository.save(dbMovie);
            return ResponseEntity.ok().build(); 
            
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }
      
    }

    @DeleteMapping(path="/movie/{id}")
    @CrossOrigin(origins ="*")
    ResponseEntity<Void> deleteMovie(@PathVariable Integer id){
        Movie deleteMovie = movieRepository.findById(id).get();
        try {
            movieRepository.delete(deleteMovie);
            return ResponseEntity.ok().build(); 
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }        

    }
    
}
