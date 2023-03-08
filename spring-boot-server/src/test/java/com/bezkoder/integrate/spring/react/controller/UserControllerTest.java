package com.bezkoder.integrate.spring.react.controller;

import com.bezkoder.integrate.spring.react.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final String uri = "/api/users";
    @Autowired
    private MockMvc mvc;


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


    @Test
    public void createProduct() throws Exception {
        User user = new User();
        user.setName("bala");
        user.setCountry("UK");
        user.setSex("Male");
        user.setAge(2);
        mvc.perform(
                        post(uri)
                                .content(mapToJson(getUser("bala", 3, "Male", "UK")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dateCreated").exists());


    }

    @Test
    public void createProductWithException() throws Exception {
        List<User> users = Arrays.asList(getUser("ba..", 3, "Male", "UK"),
                getUser("£asas", 3, "Male", "UK"),
                getUser("bala", 201, "Male", "UK"),
                getUser("bala", 3, "Male", "UK1212"),
                getUser("bala", 3, "Ki", "UK"));

        users.forEach((user) -> {
            try {
                mvc.perform(
                                post(uri)
                                        .content(mapToJson(user))
                                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest());
            } catch (Exception e) {
                fail("json formatter");
            }
        });


    }

    private User getUser(String name, int age, String sex, String country) {
        User user = new User();
        user.setName(name);
        user.setCountry(country);
        user.setSex(sex);
        user.setAge(age);
        return user;
    }
}
