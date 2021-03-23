package com.aimconsulting.testing.repository.mybatis;

import com.aimconsulting.testing.configuration.TestConfiguration;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.mybatis.repository.ResultRepositoryMyBatis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        TestConfiguration.class,
        ResultRepositoryMyBatis.class
})
@ComponentScan(basePackages="com.aimconsulting.testing")
@MapperScan("com.aimconsulting.testing.repository.impl.mybatis.mapper")
class ResultRepositoryMyBatisTest {

    @Autowired
    @Qualifier("resultRepositoryMyBatis")
    private ResultWriter writer;

    @BeforeEach
    void setUp() {
        writer.deleteAll();
    }

    @Test
    void testCreateResultsThreeDivisions() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;"));
        resultList.add(new Result("version", "1;2;"));
        resultList.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        writer.createResults(resultList);

        assertAll(
                () -> assertEquals(resultList.get(0), writer.getResult(resultList.get(0).getName()).get(0)),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName()).get(0)),
                () -> assertEquals(resultList.get(2), writer.getResult(resultList.get(2).getName()).get(0))
        );
    }

    @Test
    void testCreateResultsTwoDivisions() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;3;"));
        resultList.add(new Result("name", "ричард;жорж;мария;пьер;"));

        writer.createResults(resultList);

        assertAll(
                () -> assertEquals(resultList.get(0), writer.getResult(resultList.get(0).getName()).get(0)),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName()).get(0))
        );
    }

    @Test
    void testDeleteResult() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;3;"));
        resultList.add(new Result("name", "ричард;жорж;мария;пьер;"));
        writer.createResults(resultList);

        writer.deleteResult("id");

        assertAll(
                () -> assertEquals(new ArrayList<>(), writer.getResult(resultList.get(0).getName())),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName()).get(0))
        );
    }

}