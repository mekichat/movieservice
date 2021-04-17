package com.example.movieservice;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class MovieService {
    private final MovieServiceRepository movieServiceRepository;

    MovieService(MovieServiceRepository movieServiceRepository) {
        super();
        this.movieServiceRepository = movieServiceRepository;
    }

    List<Movie> getAll(){
        var l = new ArrayList<Movie>();
        for(Movie r : movieServiceRepository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    Movie get(Integer id){
        return movieServiceRepository.findById(id).get();
    }

    ResponseEntity<Object> add(@RequestBody Movie m){
        movieServiceRepository.save(m);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(m.getId())
        .toUri();

        return ResponseEntity.created(location).build();
    }

    Movie update(@PathVariable Integer id, @RequestBody Movie updatedMovie){
        Movie dbMovie = movieServiceRepository.findById(id).get();
        dbMovie.setActor(dbMovie.getActor());
        dbMovie.setDirector(dbMovie.getDirector());
        dbMovie.setGenre(dbMovie.getGenre());
        dbMovie.setLanguage(dbMovie.getLanguage());
        dbMovie.setReleased(dbMovie.getReleased());
        dbMovie.setTitle(dbMovie.getTitle());
       
        movieServiceRepository.save(dbMovie);       

        return dbMovie;
    }

    ResponseEntity<Void> partialUpdate(@PathVariable Integer id, @RequestBody Movie updatedMovie) {

        Movie dbMovie = movieServiceRepository.findById(id).get();

        try {
            if(updatedMovie.getTitle() != null){
                dbMovie.setTitle(dbMovie.getTitle());    
            }
            if(updatedMovie.getReleased()!= 0){ 
                dbMovie.setReleased(dbMovie.getReleased());    
            }
            if(updatedMovie.getDirector() != null){
                dbMovie.setDirector(dbMovie.getDirector());    
            }
            if(updatedMovie.getActor()!= null){ 
                dbMovie.setActor(dbMovie.getTitle());    
            }            
            if(updatedMovie.getGenre() != null){
                dbMovie.setGenre(dbMovie.getGenre());    
            }
            if(updatedMovie.getLanguage() != null){
                dbMovie.setLanguage(dbMovie.getLanguage());    
            } 
            
            movieServiceRepository.save(dbMovie);
            return ResponseEntity.ok().build(); 
            
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }      
    }

    ResponseEntity<Void> delete(@PathVariable Integer id){
        Movie deleteMovie = movieServiceRepository.findById(id).get();
        try {
            movieServiceRepository.delete(deleteMovie);
            return ResponseEntity.ok().build(); 
        } catch (Exception ex) {
            //TODO: handle exception
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }        

    }


}
