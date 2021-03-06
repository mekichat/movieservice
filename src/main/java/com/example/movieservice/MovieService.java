package com.example.movieservice;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
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
        dbMovie.setActor(updatedMovie.getActor());
        dbMovie.setDirector(updatedMovie.getDirector());
        dbMovie.setGenre(updatedMovie.getGenre());
        dbMovie.setLanguage(updatedMovie.getLanguage());
        dbMovie.setReleased(updatedMovie.getReleased());
        dbMovie.setTitle(updatedMovie.getTitle());
       
        movieServiceRepository.save(dbMovie);       

        return dbMovie;
    }

    ResponseEntity<Void> partialUpdate(@PathVariable Integer id, @RequestBody Movie updatedMovie) {

        Movie dbMovie = movieServiceRepository.findById(id).get();

        try {
            if(updatedMovie.getTitle() != null){
                dbMovie.setTitle(updatedMovie.getTitle());    
            }
            if(updatedMovie.getReleased()!= 0){ 
                dbMovie.setReleased(updatedMovie.getReleased());    
            }
            if(updatedMovie.getDirector() != null){
                dbMovie.setDirector(updatedMovie.getDirector());    
            }
            if(updatedMovie.getActor()!= null){ 
                dbMovie.setActor(updatedMovie.getTitle());    
            }            
            if(updatedMovie.getGenre() != null){
                dbMovie.setGenre(updatedMovie.getGenre());    
            }
            if(updatedMovie.getLanguage() != null){
                dbMovie.setLanguage(updatedMovie.getLanguage());    
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
