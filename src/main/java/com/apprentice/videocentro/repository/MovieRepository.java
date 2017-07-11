package com.apprentice.videocentro.repository;

import com.apprentice.videocentro.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    private static Map<Long, Movie> movieDb = new HashMap<Long, Movie>();
    private static long id = 1;

    public void addMovie(Movie movie) {
        movie.setId(id);
        movieDb.put(id, movie);
        id++;
    }

    public Movie getMovie(long id) {
        return movieDb.get(id);
    }

    public void deleteMovie(long id) {
        movieDb.remove(id);
    }

    public void updateMovie(Movie movie) {
        movieDb.put(movie.getId(), movie);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(movieDb.values());
    }

}
