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

1. Install a container manager (eg. `docker` or `podman`).
2. Run :

```bash
git clone https://github.com/Naedri/kanban-api.git
cd kanban-api
cp .env.example .env
docker compose up
```

3. Open in your web browser the Swagger UI :

```txt
http://localhost:8080/swagger-ui/index.html
```

4. You may need to sign in before accessing to the Swagger OpenAPI definition. To do so, use `admin` as Username and
   `security_pwd_changemeinprod` as Password.

## Project Progress Checklist

### Foundation

- [x] Project boots with Java 17
- [x] Clean package architecture
- [x] Constructor injection everywhere
- [x] Externalized configuration (properties)

### Persistence

- [x] PostgreSQL configured
- [x] Flyway migrations enabled
- [x] JPA entities mapped correctly
- [ ] Repository layer with pagination

### Security

- [x] User registration
- [x] JWT authentication
- [x] Passwords hashed (BCrypt)
- [x] Secured endpoints

### Task Management

- [x] Create task
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
- [x] Swagger/OpenAPI documentation

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
