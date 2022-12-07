package com.example.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ConfigServerApplication.class,
        properties = "spring.profiles.active=git",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerApplicationTest {

    @Value("${local.server.port}")
    private int port = 0;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void configurationAvailable() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.getForEntity( "/app/cloud", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void envPostAvailable() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.getForEntity("/admin/env", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}