package com.force022.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.force022.demo.constants.SecurityConfigConstans.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="kineko",roles={"ADMIN"})
    public void adminTest() {
        try {
            mockMvc.perform(get("/admin/test"))
                    .andExpect(status().isOk()).andDo(print())
                    .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.helloSay").value("Hello kineko. You are admin!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username="force022",roles={"USER"})
    public void userTest() {
        try {
            String message = "Bearer error=\"insufficient_scope\"";
            mockMvc.perform(get("/admin/test"))
                    .andExpect(status().isUnauthorized()).andDo(print())
                    .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(header().string(AUTHENTICATE_HEADER,message))
                    .andExpect(jsonPath("$.errors[0].message").value(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sessionErrorTest() {
        try {
            String message = "Bearer realm=\"Realm\"";
            mockMvc.perform(get("/admin/test").header(XHR_FIELDS_IS_AJAX_KEY,XHR_FIELDS_IS_AJAX_VALUE))
                    .andExpect(status().isUnauthorized()).andDo(print())
                    .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(header().string(AUTHENTICATE_HEADER,message))
                    .andExpect(jsonPath("$.errors[0].message").value(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}