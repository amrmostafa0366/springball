package com.game.football.service.imp;

import com.game.football.error.NotFoundException;
import com.game.football.repository.BaseRepo;
import com.game.football.service.BaseService;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MappedSuperclass
public class BaseServiceImp<T, ID> implements BaseService<T, ID> {

    @Autowired
    private BaseRepo<T, ID> baseRepo;

    @Override
    public List<T> findAll() {
        return baseRepo.findAll();
    }

    @Override
    public T findById(ID id) {
        if (existsById(id)) {
            return baseRepo.findById(id).get();
        } else {
            throw new NotFoundException("There's No Record With That ID");
        }
    }

    @Override
    public T save(T t) {
        return baseRepo.save(t);
    }

    @Override
    public boolean deleteById(ID id) {
        if (existsById(id)) {
            baseRepo.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("There's No Record With That ID");
        }
    }

    @Override
    public boolean existsById(ID id) {
        return baseRepo.existsById(id);
    }
}
