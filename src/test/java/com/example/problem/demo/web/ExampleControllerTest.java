package com.example.problem.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

@WebMvcTest(ExampleController.class)
@AutoConfigureRestTestClient
class ExampleControllerTest {
    @Autowired
    private RestTestClient restTestClient;

    @Test
    void greetingShouldReturnDefaultContent() {
        restTestClient.get().uri("/hello")
                .exchange()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.content").isEqualTo("Hello, World!");
    }

    @Test
    void paywallShouldReturnMessage() {
        restTestClient.get().uri("/paywall")
                .exchange()
                .expectHeader().contentType(APPLICATION_PROBLEM_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Show me the money");
    }
}
