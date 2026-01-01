package com.example.problem.demo.web;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ProblemResponseException extends ErrorResponseException {
    public ProblemResponseException(ProblemDetail problem) {
        this(problem, null);
    }

    public ProblemResponseException(ProblemDetail problem, @Nullable Throwable cause) {
        super(HttpStatusCode.valueOf(problem.getStatus()), problem, cause);
    }
}
