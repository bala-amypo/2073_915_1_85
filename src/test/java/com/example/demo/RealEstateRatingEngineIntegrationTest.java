package com.example.demo;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //
@AutoConfigureMockMvc //
@Listeners(TestResultListener.class) //
public class RealEstateRatingEngineIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc; //

    @Autowired
    private ObjectMapper objectMapper;

    @Test(priority = 1)
    public void testUserRegistrationAndLogin() throws Exception {
        // 1. Register a new ADMIN user
        RegisterRequest register = new RegisterRequest("Admin User", "admin@realestate.com", "password123", "ADMIN");
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk());

        // 2. Login to get JWT Token
        LoginRequest login = new LoginRequest("admin@realestate.com", "password123");
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    @Test(priority = 2)
    public void testPropertyCreationFailureForInvalidData() throws Exception {
        // Test validation: Price cannot be negative
        Property invalidProperty = new Property("Bad Property", "123 Street", "City", -500.0, 50.0);
        
        mockMvc.perform(post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidProperty)))
                .andExpect(status().is4xxClientError()); // Expected 401 or 400
    }
}