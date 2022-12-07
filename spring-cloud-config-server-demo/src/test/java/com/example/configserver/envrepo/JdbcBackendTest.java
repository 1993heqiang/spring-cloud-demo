package com.example.configserver.envrepo;

import com.example.configserver.ConfigServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ConfigServerApplication.class,
        properties = "spring.profiles.active=jdbc",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JdbcBackendTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void jdbcSimpleTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/test/jdbc", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).contains("first", "second", "third");
    }
}
