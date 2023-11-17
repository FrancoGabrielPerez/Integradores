package com.microscooter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class ScooterControllerTest {
    @Autowired
    private MockMvc mock;

    @Autowired
    private ScooterController controller;

    private static String token;

    @BeforeAll
    public static void setup() {
        token = getSystemToken();
    }

    private static String getSystemToken() {
        Map<String, String> body = new HashMap<>();
        body.put("email", "System@system");
        body.put("password", "System");

        ResponseEntity<String> response = new RestTemplate()
                .postForEntity("http://localhost:8081/auth/acceder", body, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Error al obtener el token");
        return response.getBody();
    }

    @Test
	void contextLoads() throws Exception {
		//assertNotNull(controller).isNotNull();
	}

    @Test
    public void getMonopatines_ok()throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/monopatines")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", token))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()); 
    }
}
