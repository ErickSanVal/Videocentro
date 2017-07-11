package com.apprentice.videocentro.service;

import com.apprentice.videocentro.model.Movie;
import com.apprentice.videocentro.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie findById(long id) {
        return movieRepository.getMovie(id);
    }

    public void saveMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteMovie(id);
    }

}
