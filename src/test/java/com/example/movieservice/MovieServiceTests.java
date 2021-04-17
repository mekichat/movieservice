package com.example.movieservice;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.assertj.core.api.Assertions.assertThat; 

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovieServiceTests {
    MovieServiceRepository movieServiceRepository;
    ArrayList<Movie> testlist;
    Movie testMovie;
    @BeforeEach
    void init()
    {
        testlist = new ArrayList<Movie>();
        testlist.add(new Movie());
        testlist.add(new Movie());
        testlist.add(new Movie());
        testMovie = new Movie();      
        testMovie.setId(4);
        testMovie.setTitle("Lord of the Rings");
        testMovie.setReleased(2001);
        
        movieServiceRepository = Mockito.mock(MovieServiceRepository.class); 
        when(movieServiceRepository.findAll()).thenReturn(testlist);
        when(movieServiceRepository.findById(testMovie.getId())).thenReturn(Optional.of(testMovie));
    }

    @Test
    void getAllMoviesShouldReturnAllRecords()
    {
        var allMovies = new MovieService(movieServiceRepository);
        assertArrayEquals( testlist.toArray() ,allMovies.getAll().toArray());            
    }

    @Test
    void getSingleMovieShouldReturnASingleRecord()
    {
        var singleMovie = new MovieService(movieServiceRepository);        
        assertThat(testMovie).usingRecursiveComparison().isEqualTo(singleMovie.get(4)); 
                  
    }
}
