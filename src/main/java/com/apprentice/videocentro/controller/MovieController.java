package com.apprentice.videocentro.controller;

import com.apprentice.videocentro.model.Movie;
import com.apprentice.videocentro.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        final List<Movie> allMovies = movieService.findAllMovies();
        if (allMovies.isEmpty()) {
            return new ResponseEntity<String>("No movies found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Movie>>(allMovies, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        try {
            movieService.saveMovie(movie);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error creating movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Created movie with id " + movie.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie currentMovie = movieService.findById(id);
        if (currentMovie != null) {
            currentMovie.setTitle(movie.getTitle());
            currentMovie.setGenre(movie.getGenre());
            currentMovie.setRating(movie.getRating());
            movieService.updateMovie(currentMovie);
        } else {
            return new ResponseEntity<String>("Error updating movie movie with id: " + id + " doesn't exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Updated movie with id " + id, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable long id) {
        Movie currentMovie = movieService.findById(id);
        if (currentMovie == null) {
            return new ResponseEntity<String>("Movie with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        movieService.deleteMovie(currentMovie.getId());
        return new ResponseEntity<String>("Deleted movie with id " + id, HttpStatus.OK);
    }
}
