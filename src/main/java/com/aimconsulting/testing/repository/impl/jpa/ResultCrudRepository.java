package com.aimconsulting.testing.repository.impl.jpa;

import com.aimconsulting.testing.model.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultCrudRepository extends CrudRepository<Result, Integer> {
}
