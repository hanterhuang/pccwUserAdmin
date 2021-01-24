package com.pccw.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @ClassName UserControllerTest
 * @Author Hanter
 * @Description mock restful test
 * @Date 2021/01/24
**/
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserControllerTest {
	
	@Value("${useradmin.version}")
	private String version;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @Order(1) 
    public void whenHeartbeatSuccess() throws Exception {
        String result = mockMvc.perform(get("/heartbeat")
        		)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value(version))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    
    // use initial mock data to query user info by token
    @Test
    @Order(2) 
    public void whenGetUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user")
                	.param("token", "hanter1")
        		)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first").value("Hanter"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    
    @Test
    public void whenGetUserInfoFailWithOutInput() throws Exception {
        String result = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    
    @Test
    public void whenLoginSuccess() throws Exception {
        String result = mockMvc.perform(post("/user/login")
        		.param("email", "hanter@126.com")
                .param("password", "123")
                )
        		.andExpect(status().isOk())
        		.andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    
    @Test
    public void whenCreateUserSuccess() throws Exception {
        String content = "{\"first\":\"jacky\",\"password\":\"111\",\"email\":\"jack@126.com\",\"last\":\"chen\"}";
        String result = mockMvc.perform(post("/user")
        			.contentType(MediaType.APPLICATION_JSON)
        			.content(content)
        		)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    
    @Test
    public void whenLogoutSuccess() throws Exception {
        String result = mockMvc.perform(post("/user/logout")
        			.param("token", "hanter1")
                )
        		.andExpect(status().isOk())
        		.andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


}



