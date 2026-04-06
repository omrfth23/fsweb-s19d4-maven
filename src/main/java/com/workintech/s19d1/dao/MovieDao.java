package com.workintech.s19d1.dao;

import com.workintech.s19d1.entity.Movie;
import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    void delete(Movie movie);
}