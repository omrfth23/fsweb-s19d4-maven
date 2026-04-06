package com.workintech.s19d1.dao;

import com.workintech.s19d1.entity.Actor;
import java.util.List;

public interface ActorDao {
    List<Actor> findAll();
    Actor findById(Long id);
    Actor save(Actor actor);
    void delete(Actor actor);
}