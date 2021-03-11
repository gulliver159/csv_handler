package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ResultRepositoryTest {

    private static ResultWriter writer;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:data.sql")
                .build();

        writer = new ResultRepository(new JdbcTemplate(dataSource));
    }

    @BeforeEach
    void setUp() {
        writer.deleteAll();
    }

    @Test
    void testCreateResults1() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;"));
        resultList.add(new Result("version", "1;2;"));
        resultList.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        writer.createResults(resultList);

        assertAll(
                () -> assertEquals(resultList.get(0), writer.getResult(resultList.get(0).getName())),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName())),
                () -> assertEquals(resultList.get(2), writer.getResult(resultList.get(2).getName()))
        );
    }

    @Test
    void testCreateResults2() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;3;"));
        resultList.add(new Result("name", "ричард;жорж;мария;пьер;"));
        resultList.add(new Result("sex", "м;ж;"));

        writer.createResults(resultList);

        assertAll(
                () -> assertEquals(resultList.get(0), writer.getResult(resultList.get(0).getName())),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName())),
                () -> assertEquals(resultList.get(2), writer.getResult(resultList.get(2).getName()))
        );
    }

}