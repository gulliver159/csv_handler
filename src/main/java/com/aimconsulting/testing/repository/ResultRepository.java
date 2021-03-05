package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResultRepository implements ResultWriter {
    private final JdbcTemplate template;

    public ResultRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void createResults(List<Result> resultList) {
        template.batchUpdate("INSERT INTO results(name, content) VALUES(?, ?)", new BatchPreparedStatementSetter() {
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
}
