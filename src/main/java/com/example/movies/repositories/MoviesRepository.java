package com.example.movies.repositories;

import com.example.movies.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

}