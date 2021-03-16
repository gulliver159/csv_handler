package com.aimconsulting.testing.mapper;

import com.aimconsulting.testing.model.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultMapper {
    void deleteAll();
    void createResults(List<Result> resultList);
    Result getResult(String name);
}
