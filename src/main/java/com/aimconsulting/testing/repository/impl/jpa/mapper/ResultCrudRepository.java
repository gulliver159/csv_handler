package com.aimconsulting.testing.repository.impl.jpa.mapper;

import com.aimconsulting.testing.model.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ResultCrudRepository extends CrudRepository<Result, Integer> {
    List<Result> findAllByName(String name);
    @Transactional
    void deleteByName(String name);
}
