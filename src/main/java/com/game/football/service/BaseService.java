package com.game.football.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseService<T, ID> {
    public List<T> findAll();

    public T findById(ID id);

    public T save(T t);
    public void deleteById(ID id);

    boolean existsById(ID id);
}
