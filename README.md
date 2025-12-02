## Project Overview

This project is a Spring Boot 3.5 REST and GraphQL API for managing a planetary system. 
It models planets, their moons, and application users, backed by H2 and Spring Data JPA.

**API, Security and Validation**

The REST layer exposes full CRUD plus custom queries for planets and moons, 
using DTOs for all input/output and Jakarta validation to enforce data rules. 
Security is implemented with Spring Security Basic Auth backed by a persisted User entity and BCrypt-hashed passwords, 
enforcing three roles (ADMIN, STAFF, STUDENT) with different permissions on the API and the GraphQL user management operations.

**Cross-Cutting Concerns and Tooling**

Cross-cutting concerns are handled via a centralized (but currently disabled for Swagger compatibility) exception handler
and an AspectJ-based logging aspect that logs controller, service, and repository calls. 
The API is documented with Swagger/OpenAPI, making it easy to explore and test endpoints via Swagger UI and GraphiQL.


## Project Requirements

- Requirements: Java 17, Maven.  
- The app starts on `http://localhost:8080`.  
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`  
- GraphiQL: `http://localhost:8080/graphiql`  

## Sample users and roles

All users use HTTP Basic auth.

- `admin` / `admin123` – role `ADMIN`  
  - Full access to all REST endpoints and GraphQL User operations.  
- `staff` / `staff123` – role `STAFF`  
  - Can create/update/delete planets and moons (no user management).  
- `student` / `student123` – role `STUDENT`  
  - Read‑only access to planets and moons.

## Sample data to create

Use these via Swagger:

**Create a planet with a moon** – `POST /api/planets` (as `admin` or `staff`):

```json
{
  "name": "Earth",
  "radius": 6371,
  "mass": 5.972e24,
  "moons": [
    {
      "name": "Moon",
      "radius": 1737,
      "mass": 7.35e22
    }
  ]
}
```

Then note the returned planet `id`, e.g. `1`.

**Create a moon attached to an existing planet** – `POST /api/moons`:

```json
{
  "name": "Europa",
  "radius": 1560.8,
  "mass": 4.8e22,
  "planetId": 1
}
```

After that, `GET /api/planets` and `GET /api/moons` will return data.

## Known limitations / notes

- A `GlobalExceptionHandler` class is implemented for centralized error responses, but the `@RestControllerAdvice` annotation is currently commented out because enabling it causes a 500 error on `/v3/api-docs` due to a Springdoc/Spring Boot compatibility issue.  
- With the annotation disabled, validation and not‑found errors fall back to Spring’s default error format, but Swagger/OpenAPI works reliably.
