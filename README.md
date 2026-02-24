# Kanban API

A task manager API built with **Java 21** and **Spring Boot 4**.
The objective is to demonstrate clean architecture, secure REST design, and modern backend practices.

## Tech Stack

- Java 17
- Spring Boot 3
- Maven (mono-module, JAR packaging)
- PostgreSQL
- Spring Data JPA
- Spring Security with JWT
- Flyway
- Testcontainers
- OpenAPI (springdoc)

## Run Locally

```bash
mvn clean verify
mvn spring-boot:run
```

Swagger UI:

```txt
http://localhost:8080/swagger-ui.html
```

## Project Progress Checklist

### Foundation

- [ ] Project boots with Java 17
- [ ] Clean package architecture
- [ ] Constructor injection everywhere
- [ ] Externalized configuration (properties)

### Persistence

- [ ] PostgreSQL configured
- [ ] Flyway migrations enabled
- [ ] JPA entities mapped correctly
- [ ] Repository layer with pagination

### Security

- [ ] User registration
- [ ] JWT authentication
- [ ] Passwords hashed (BCrypt)
- [ ] Secured endpoints

### Task Management

- [ ] Create task
- [ ] Update task
- [ ] Task state machine enforced
- [ ] Optimistic locking on Task

### Collaboration

- [ ] Group creation
- [ ] Membership management
- [ ] Task sharing rules

### API Quality

- [ ] Validation layer
- [ ] Proper HTTP status codes
- [ ] Pagination and filtering
- [ ] Swagger/OpenAPI documentation

### Testing and Delivery

- [ ] Integration tests (Testcontainers)
- [ ] CI pipeline
- [ ] Dockerized application
- [ ] Health probes enabled

## Architecture Highlights

- Layered architecture (controller -> service-> repository)
- DTO / Entity separation
- Repository pattern via Spring Data
- Guarded task state transitions
- Optimistic locking for concurrency safety
- Stateless JWT security
