package com.aimconsulting.testing.repository.impl.jpa.mapper;

import com.aimconsulting.testing.model.Result;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ResultCrudRepository extends CrudRepository<Result, Integer> {
    List<Result> findAllByName(String name);
    @Transactional
    void deleteByName(String name);
}
