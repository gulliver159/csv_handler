package com.aimconsulting.testing.repository.impl.mybatis.mapper;

import com.aimconsulting.testing.model.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResultMapper {

    @Delete("delete from results")
    void deleteAll();

    @Delete("delete from results where name = #{name}")
    void deleteResult(String name);

    @Insert({
        "<script>",
            "insert into results(name, content) ",
            "values ",
            "<foreach item='item' collection='resultList' separator=',' >",
                "( #{item.name}, #{item.content} )",
            "</foreach>",
        "</script>"
    })
    void createResults(List<Result> resultList);

    @Select("select name, content from results where name = #{name}")
    List<Result> getResult(String name);
}
