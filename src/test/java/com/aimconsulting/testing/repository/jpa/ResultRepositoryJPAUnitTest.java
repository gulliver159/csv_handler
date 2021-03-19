package com.aimconsulting.testing.repository.jpa;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.jpa.ResultRepositoryJPA;
import com.aimconsulting.testing.repository.impl.jpa.mapper.ResultCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ResultRepositoryJPAUnitTest {

    private final ResultCrudRepository crudRepository = Mockito.mock(ResultCrudRepository.class);
    private final ResultWriter writer = new ResultRepositoryJPA(crudRepository);

    @BeforeEach
    void setUp() {
        writer.deleteAll();
    }

    @Test
    void testCreateResults() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;"));
        resultList.add(new Result("version", "1;2;"));

        writer.createResults(resultList);

        verify(crudRepository, times(1)).saveAll(resultList);
    }

    @Test
    void testGetResult() {
        writer.getResult("id");
        verify(crudRepository, times(1)).findAllByName("id");
    }

    @Test
    void testDeleteResult() {
        writer.deleteResult("id");
        verify(crudRepository, times(1)).deleteByName("id");
    }
}