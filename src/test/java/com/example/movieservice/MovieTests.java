package com.example.movieservice;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MovieTests {
    @Test
    void movieTicketPriceReturnsCorrectValue(){
        Integer numberOfPersons = 10;
        Movie movie = new Movie();
        movie.setActor("Ryan Gosling");
        movie.setDirector("Nick Cassavetes");
        movie.setGenre("Romance");
        movie.setLanguage("en");
        movie.setTitle("The Notebook");
        movie.setReleased(2004);        
        
        assertThat(movie.movieTicketPrice(numberOfPersons)).isEqualTo(2500);
    
    }
}
