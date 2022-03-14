package com.aimconsulting.testing.repository.jpa;

import com.aimconsulting.testing.configuration.TestConfiguration;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.jpa.mapper.ResultCrudRepository;
import com.aimconsulting.testing.repository.impl.jpa.repository.ResultRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        TestConfiguration.class,
        ResultRepositoryJPA.class
})
@ComponentScan(basePackages="com.aimconsulting.testing")
@EnableJpaRepositories(basePackageClasses = ResultCrudRepository.class)
class ResultRepositoryJPATest {

    @Autowired
    @Qualifier("resultRepositoryJPA")
    private ResultWriter writer;

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

        assertAll(
                () -> assertEquals(resultList.get(0), writer.getResult(resultList.get(0).getName()).get(0)),
                () -> assertEquals(resultList.get(1), writer.getResult(resultList.get(1).getName()).get(0)),
                () -> assertEquals(resultList.get(2), writer.getResult(resultList.get(2).getName()).get(0))
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

    @Test
    void testCreateResultsByUser() {
        List<Result> expectedAnswer = new ArrayList<>();
        expectedAnswer.add(new Result("id", "0;1;2;"));
        expectedAnswer.add(new Result("version", "1;2;"));
        expectedAnswer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        User user = new User("Roma");
        for (Result result : expectedAnswer) {
            result.setUser(user);
        }

        writer.createResultsByUser(expectedAnswer);

        List<Result> actualAnswerByResultName = new ArrayList<>();
        actualAnswerByResultName.add(writer.getResult(expectedAnswer.get(0).getName()).get(0));
        actualAnswerByResultName.add(writer.getResult(expectedAnswer.get(1).getName()).get(0));
        actualAnswerByResultName.add(writer.getResult(expectedAnswer.get(2).getName()).get(0));

        for (Result result : actualAnswerByResultName) {
            result.getUser().setResults(null);
        }

        List<Result> actualAnswerByUsername = writer.getResultsByUsername(user.getName());
        for (Result result : actualAnswerByUsername) {
            result.getUser().setResults(null);
        }

        assertAll(
                () -> assertEquals(expectedAnswer, actualAnswerByResultName),
                () -> assertEquals(expectedAnswer, actualAnswerByUsername)
        );
    }

    @Test
    void testDeleteResultsByUsername() {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result("id", "0;1;2;3;"));
        resultList.add(new Result("name", "ричард;жорж;мария;пьер;"));

        User user = new User("Roma");
        for (Result result : resultList) {
            result.setUser(user);
        }

        writer.createResults(resultList);

        writer.deleteResultsByUsername(user.getName());

        assertEquals(new ArrayList<>(), writer.getResultsByUsername(user.getName()));
    }

}