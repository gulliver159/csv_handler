package com.aimconsulting.testing.repository.impl.mybatis.mapper;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import org.apache.ibatis.annotations.*;

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

    @Select("select results.name, content, #{username} as username from results left outer join users on results.user_id = users.id " +
            "where users.name = #{username}")
    @org.apache.ibatis.annotations.Result(property = "user", column = "username", one = @One(select = "getUser"))
    List<Result> getResultsByUsername(String username);

    @Select("select id, name from users where name = #{name}")
    User getUser(String name);

    @Insert("insert into users(name) values (#{user.name})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void createUser(@Param("user") User user);

    @Insert({
            "<script>",
            "insert into results(name, content, user_id) ",
            "values ",
            "<foreach item='item' collection='resultList' separator=',' >",
            "( #{item.name}, #{item.content}, #{user.id} )",
            "</foreach>",
            "</script>"
    })
    void createResultsByUser(@Param("resultList") List<Result> resultList, @Param("user") User user);

    @Delete("delete from results where user_id = (select id from users where name = #{username})")
    void deleteResultsByUsername(String username);
}
