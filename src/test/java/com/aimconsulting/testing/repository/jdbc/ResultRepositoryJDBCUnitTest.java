package com.aimconsulting.testing.repository.jdbc;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.jdbc.ResultRepositoryJDBC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ResultRepositoryJDBCUnitTest {

    private final JdbcTemplate template = Mockito.mock(JdbcTemplate.class);
    private final ResultWriter writer = new ResultRepositoryJDBC(template);

    @BeforeEach
    void setUp() {
        writer.deleteAll();
    }

    @Test
    void testCreateResults() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;"));
        resultList.add(new Result("version", "1;2;"));
        resultList.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        writer.createResults(resultList);

        String expectedSQL = "insert into results(name, content) values(?, ?)";
        verify(template, times(1))
                .batchUpdate(eq(expectedSQL), any(BatchPreparedStatementSetter.class));
    }

    @Test
    void testDeleteResult() {
        String id = "id";
        writer.deleteResult(id);

        String expectedSQL = "delete from results where name = ?";
        verify(template, times(1)).update(expectedSQL, id);
    }

    @Test
    void testGetResult() {
        String id = "id";
        writer.getResult(id);

        String expectedSQL = "select name, content from results where name = ?";
        verify(template, times(1)).query(eq(expectedSQL), eq(new Object[] {id}), any(RowMapper.class));
    }

}