package com.example.configserver.envrepo;

import com.example.configserver.ConfigServerApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ConfigServerApplication.class,
        properties = {"spring.profiles.active=redis"},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableRedisRepositories
class RedisBackendTest {
    public static final String APPLICATION_NAME = "redis_app";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static RedisServer redisServer;

    @BeforeAll
    static void beforeAll() {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @BeforeEach
    public void before() {
        Map<String, String> data = new HashMap<>();
        data.put("server.port", "8100");
        data.put("sample.topic.name", "test");
        data.put("test.property1", "property1");
        redisTemplate.opsForHash().putAll(APPLICATION_NAME + "-dev", data);
    }

    @Test
    void jdbcSimpleTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/" + APPLICATION_NAME + "/dev", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).contains("8100");
    }

    @AfterAll
    static void afterAll() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
