package com.moses.sorting.service;


import com.moses.sorting.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieService {
    private final List<Movie> movies = new ArrayList<>();
    private int currentId = 1;

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Optional<Movie> getMovieById(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst();
    }

    public Movie addMovie(Movie movie) {
        movie.setId(currentId++);
        movies.add(movie);
        return movie;
    }

    public boolean updateMovie(int id, Movie updatedMovie) {
        return getMovieById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            return true;
        }).orElse(false);
    }

    public boolean deleteMovie(int id) {
        return movies.removeIf(movie -> movie.getId() == id);
    }
}
