package com.aimconsulting.testing.repository.impl.jpa;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.jpa.mapper.ResultCrudRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class ResultRepositoryJPA implements ResultWriter {

    private final ResultCrudRepository resultCrudRepository;

    public ResultRepositoryJPA(ResultCrudRepository resultCrudRepository) {
        this.resultCrudRepository = resultCrudRepository;
    }

    public void deleteAll() {
        resultCrudRepository.deleteAll();
    }

    public void deleteResult(String name) {
        resultCrudRepository.deleteByName(name);
    }

    public void createResults(List<Result> resultList) {
        resultCrudRepository.saveAll(resultList);
    }

    public List<Result> getResult(String name) {
        return resultCrudRepository.findAllByName(name);
    }
}
