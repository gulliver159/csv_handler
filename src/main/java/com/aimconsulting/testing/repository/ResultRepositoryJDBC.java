package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultRepositoryJDBC implements ResultWriter {
    private final JdbcTemplate template;

    public ResultRepositoryJDBC(JdbcTemplate template) {
        this.template = template;
    }

    public void deleteAll() {
        template.update("delete from results");
    }

    public void deleteResult(String name) {
        template.update("delete from results where name = ?", name);
    }

    public void createResults(List<Result> resultList) {
        template.batchUpdate("insert into results(name, content) values(?, ?)",
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Result result = resultList.get(i);
                ps.setString(1, result.getName());
                ps.setString(2, result.getContent());
            }
            @Override
            public int getBatchSize() {
                return resultList.size();
            }
        });
    }

    public List<Result> getResult(String name) {
        return template.query("select name, content from results where name = ?", new Object[]{name},
                (rs, rowNum) -> new Result(rs.getString("name"), rs.getString("content")));
    }
}
