# Problem Demo

A Spring Boot demonstration project showcasing the use of RFC 9457 Problem Details for HTTP APIs for standardized error responses.

## Overview

This project demonstrates how to implement standardized error responses in Spring Boot applications using the `ProblemDetail` API introduced in Spring Framework 6.0. It provides a simple example of returning structured error information in a machine-readable format.

## Key Concepts

### ProblemDetail API

The `ProblemDetail` class in Spring Framework provides a standardized way to represent error responses according to RFC 9457. It includes:

- `type`: URI reference identifying the problem type
- `title`: Human-readable summary of the problem
- `status`: HTTP status code
- `detail`: Human-readable explanation specific to this occurrence
- Custom properties (like `message` in this example)

### Custom Exception

The `ProblemResponseException` extends Spring's `ErrorResponseException` to provide a convenient way to throw problem responses:

```java
var problem = ProblemDetail.forStatus(HttpStatus.PAYMENT_REQUIRED);
problem.setProperty("message", "Show me the money");
throw new ProblemResponseException(problem);
```

Spring automatically converts this exception into an HTTP response with the appropriate status code and `application/problem+json` content type, if the property `spring.mvc.problemdetails.enabled` set to true in application properties.

## Features

- **Standard Error Responses**: Implements RFC 9457 Problem Details for consistent error handling
- **Custom Exception Handling**: Custom `ProblemResponseException` for throwing problem responses
- **RESTful API**: Simple REST endpoints demonstrating both successful and error responses
- **Web Layer Tests**: Unit tests using Spring Boot's `RestTestClient`

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle (wrapper included)

### Running the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`.

### Building the Project

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

## API Endpoints

### GET `/hello`

Returns a simple greeting message.

**Response (200 OK):**
```json
{
  "content": "Hello, World!"
}
```

**Content-Type:** `application/json`

### GET `/paywall`

Demonstrates a problem response with HTTP 402 Payment Required status.

**Response (402 Payment Required):**
```json
{
  "type": "about:blank",
  "title": "Payment Required",
  "status": 402,
  "message": "Show me the money"
}
```

**Content-Type:** `application/problem+json`

## Testing with HTTP Client

An `example.http` file is included for testing with IntelliJ IDEA's HTTP Client or similar tools:

```http
### GET request to message endpoint
GET http://localhost:8080/hello

### GET request to problem endpoint
GET http://localhost:8080/paywall
```

## Further Reading

- [RFC 9457 - Problem Details for HTTP APIs](https://www.rfc-editor.org/rfc/rfc9457.html)
- [Spring Framework Error Responses](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html)
- [Spring Boot Properties Documentation](https://docs.spring.io/spring-boot/appendix/application-properties/index.html#application-properties.web.spring.mvc.problemdetails.enabled)
