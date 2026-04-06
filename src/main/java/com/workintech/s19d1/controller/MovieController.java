package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorMovieRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ActorService actorService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody ActorMovieRequest request) {
        Movie movie = request.getMovie();
        Actor actor = request.getActor();

        HollywoodValidation.validateMovie(movie);

        if (actor != null) {
            movie.addActor(actor);
            actor.addMovie(movie);
        }
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie existing = movieService.findById(id);
        existing.setName(updatedMovie.getName());
        existing.setDirectorName(updatedMovie.getDirectorName());
        existing.setRating(updatedMovie.getRating());
        existing.setReleaseDate(updatedMovie.getReleaseDate());
        return movieService.save(existing);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return movie;
    }
}
