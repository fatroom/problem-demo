package com.example.problem.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/hello")
    public Message hello() {
        return new Message("Hello, World!");
    }

    @GetMapping("/paywall")
    public Message paywall() {
        var problem = ProblemDetail.forStatus(HttpStatus.PAYMENT_REQUIRED);
        problem.setProperty("message", "Show me the money");
        throw new ProblemResponseException(problem);
    }
}
