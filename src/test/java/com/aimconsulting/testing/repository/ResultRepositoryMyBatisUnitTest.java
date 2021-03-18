package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.mapper.ResultMapper;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ResultRepositoryMyBatisUnitTest {

    private final ResultMapper mapper = Mockito.mock(ResultMapper.class);
    private final ResultWriter writer = new ResultRepositoryMyBatis(mapper);

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

        verify(mapper, times(1)).createResults(resultList);
    }

    @Test
    void testGetResult() {
        writer.getResult("id");
        verify(mapper, times(1)).getResult("id");
    }

    @Test
    void testDeleteResult() {
        writer.deleteResult("id");
        verify(mapper, times(1)).deleteResult("id");
    }
}