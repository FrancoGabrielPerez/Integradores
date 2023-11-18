package com.microuseraccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class MicroUserAccountApplicationTests {

    private final WebApplicationContext context;
    private static final String AUTH_URL = "http://localhost:8081/auth/acceder";
    private MockMvc mockMvc;

    public MicroUserAccountApplicationTests(WebApplicationContext context) {
        this.context = context;
    }

    private static String getSystemToken() {
        Map<String, String> body = new HashMap<>();
        body.put("email", "System@system");
        body.put("password", "System");

        ResponseEntity<String> response = new RestTemplate()
                .postForEntity(AUTH_URL, body, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Error al obtener el token");
        String responseBody = response.getBody();
        if (responseBody != null) {
            return responseBody.replace("{\"token\":\"", "").replace("\"}", "");
        } else {
            throw new IllegalStateException("Response body is null");
        }
    }

    @Test
    void testMicroservicio() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Realizar la autenticación y obtener el token
        String token = getSystemToken();
       
        // Utilizar el token para realizar una solicitud protegida
        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/saldo/obtener/1")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void testInabilitarCuenta() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Realizar la autenticación y obtener el token
        String token = getSystemToken();
       
        // Utilizar el token para realizar una solicitud protegida
        mockMvc.perform(MockMvcRequestBuilders.put("/cuentas/suspender/3")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
