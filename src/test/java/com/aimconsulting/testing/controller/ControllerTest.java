package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.configuration.TestConfiguration;
import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.CreateByUserDtoRequest;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import com.aimconsulting.testing.repository.ResultWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        TestConfiguration.class,
        ResultWriter.class
})
@AutoConfigureMockMvc
@ComponentScan(basePackages="com.aimconsulting.testing")
class ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(post("/csv/clear"));
    }

    @Test
    void testCreateResults() throws Exception {
        ContentDtoRequest request = new ContentDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;");

        List<Result> answer = new ArrayList<>();
        answer.add(new Result("id", "0;1;2;"));
        answer.add(new Result("version", "1;2;"));
        answer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        mockMvc.perform(
                post("/csv")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultList[0].name").value(answer.get(0).getName()))
                .andExpect(jsonPath("$.resultList[0].content").value(answer.get(0).getContent()))
                .andExpect(jsonPath("$.resultList[1].name").value(answer.get(1).getName()))
                .andExpect(jsonPath("$.resultList[1].content").value(answer.get(1).getContent()))
                .andExpect(jsonPath("$.resultList[2].name").value(answer.get(2).getName()))
                .andExpect(jsonPath("$.resultList[2].content").value(answer.get(2).getContent()));
    }

    @Test
    void testGetResult() throws Exception {
        ContentDtoRequest request = new ContentDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;");

        mockMvc.perform(
                post("/csv")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        Result answer = new Result("id", "0;1;2;");

        mockMvc.perform(
                get("/csv/id")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(answer.getName()))
                .andExpect(jsonPath("$.content").value(answer.getContent()));
    }

    @Test
    void testDeleteResult() throws Exception {
        ContentDtoRequest request = new ContentDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;");

        mockMvc.perform(
                post("/csv")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
                delete("/csv/id")
        )
                .andExpect(status().isOk());

        Result answer = new Result("id", "");

        mockMvc.perform(
                get("/csv/id")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(answer.getName()))
                .andExpect(jsonPath("$.content").value(answer.getContent()));
    }

    @Test
    void testCreateResultsByUser() throws Exception {
        User user = new User("Roma");
        CreateByUserDtoRequest request = new CreateByUserDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;", user);

        List<Result> answer = new ArrayList<>();
        answer.add(new Result("id", "0;1;2;"));
        answer.add(new Result("version", "1;2;"));
        answer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        mockMvc.perform(
                post("/csv/user")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultList[0].name").value(answer.get(0).getName()))
                .andExpect(jsonPath("$.resultList[0].content").value(answer.get(0).getContent()))
                .andExpect(jsonPath("$.resultList[1].name").value(answer.get(1).getName()))
                .andExpect(jsonPath("$.resultList[1].content").value(answer.get(1).getContent()))
                .andExpect(jsonPath("$.resultList[2].name").value(answer.get(2).getName()))
                .andExpect(jsonPath("$.resultList[2].content").value(answer.get(2).getContent()));
    }

    @Test
    void testGetResultsByUsername() throws Exception {
        User user = new User("Roma");
        CreateByUserDtoRequest request = new CreateByUserDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;", user);
        mockMvc.perform(
                post("/csv/user")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        List<Result> answer = new ArrayList<>();
        answer.add(new Result("id", "0;1;2;"));
        answer.add(new Result("version", "1;2;"));
        answer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        mockMvc.perform(
                get("/csv/user/Roma")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultList[0].name").value(answer.get(0).getName()))
                .andExpect(jsonPath("$.resultList[0].content").value(answer.get(0).getContent()))
                .andExpect(jsonPath("$.resultList[1].name").value(answer.get(1).getName()))
                .andExpect(jsonPath("$.resultList[1].content").value(answer.get(1).getContent()))
                .andExpect(jsonPath("$.resultList[2].name").value(answer.get(2).getName()))
                .andExpect(jsonPath("$.resultList[2].content").value(answer.get(2).getContent()));
    }

    @Test
    void testDeleteResultsByUsername() throws Exception {
        User user = new User("Roma");
        CreateByUserDtoRequest request = new CreateByUserDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;", user);

        mockMvc.perform(
                post("/csv/user")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
                delete("/csv/user/Roma")
        )
                .andExpect(status().isOk());

        mockMvc.perform(
                get("/csv/user/Roma")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultList").value(new ArrayList<>()));
    }
}