**Project Overview**

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
