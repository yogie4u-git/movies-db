package com.example.movies.controllers;


import com.example.movies.exception.ResourceNotFoundException;
import com.example.movies.models.Movie;
import com.example.movies.repositories.MoviesRepository;
import jdk.javadoc.doclet.Reporter;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.BadBinaryOpValueExpException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MoviesController {

    private final MoviesRepository repository;

    public MoviesController(MoviesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {

        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for Id: " + id));

        return ResponseEntity.ok().body(movie);
    }

    @PostMapping
    public Movie createMovie(@Validated @RequestBody Movie movie) {

        return repository.save(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") long id,
                                             @Validated @RequestBody Movie movieDetails) throws ResourceNotFoundException {

        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for Id: " + id));

        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setDirector(movieDetails.getDirector());
        movie.setTitle(movieDetails.getTitle());

        Movie updatedMovie = repository.save(movie);

        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for Id: " + id));

        repository.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
