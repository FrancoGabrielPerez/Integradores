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
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class ScooterControllerTest {
    @Autowired
    private MockMvc mock;

    @Autowired
    private ScooterController controller;

    private static String token;

    @LocalServerPort
	private int port = 8002;

	@Autowired
	private TestRestTemplate restTemplate;

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
        System.out.println(response.getBody());
        return response.getBody();
    }

    @Test
	void contextLoads() throws Exception {
		assertNotNull(controller);
	}

    /*
    @Test
    public void getMonopatines_ok()throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/monopatines")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", token))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()); 
        this.mock.perform(MockMvcRequestBuilders.get("/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", token))
                            .andExpect(MockMvcResultMatchers.status().isOk());
    }*/

    @Test
    void greetingShouldReturnOkStatusWithAuthorization() {
        // Configurar las cabeceras con el token de autorización
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // Realizar la solicitud GET con autorización
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+ port + "/monopatines", String.class);

        // Verificar el código de estado y otras condiciones según sea necesario
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }
    
}
