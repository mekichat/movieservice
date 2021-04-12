package com.example.movieservice;

import org.springframework.data.repository.CrudRepository;

public interface MovieServiceRepository extends CrudRepository<Movie, Integer> {
    
}
