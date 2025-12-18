# Farm-to-Consumer Supply Platform

## Tech Stack
- Java 17, Spring Boot
- MySQL
- React
- Docker, GitHub Actions
- AWS EC2
- Architecture: Clean Architecture + DDD-lite

---

## Phase 0 — End-to-End Deployment (Production Foundation)

### Objective
Establish a complete production-ready pipeline:
React → Spring Boot → MySQL → Docker → AWS EC2 → CI/CD

No business logic or security in this phase. Focus purely on deployment correctness.

### What Was Implemented
- Spring Boot backend deployed on AWS EC2
- React frontend served via Nginx
- MySQL running in Docker on EC2
- CI/CD pipeline using GitHub Actions
- Docker images pushed to Docker Hub
- Self-hosted GitHub Actions runner on EC2

### Backend CI/CD Flow
1. Code pushed to `main`
2. GitHub Actions builds Spring Boot app
3. Docker image created and pushed to Docker Hub
4. EC2 runner pulls image and restarts container

### MySQL Setup (Docker on EC2)
```bash
docker run \
 --name farm-mysql \
 -e MYSQL_ROOT_PASSWORD=rootpassword \
 -e MYSQL_DATABASE=farmdb \
 -e MYSQL_USER=farmuser \
 -e MYSQL_PASSWORD=farmpassword \
 -p 3306:3306 \
 -d mysql:latest
```

### Key Learnings (Phase 0)
- CI/CD with self-hosted runners
- Docker image lifecycle
- Container-to-DB networking
- Phase-wise delivery strategy

---

## Phase 1 — Core Domain Modeling & Transactions

### Objective
Implement real business logic with strong domain boundaries and transactional integrity.

### Domain Model
- Product: Core entity (what is sold)
- Inventory: Supporting domain (stock control)
- Order: Aggregate root (order lifecycle)

### Package Structure
```
com.farm
 ├── product
 ├── inventory
 ├── order
 └── common
```

### Inventory Design
- Inventory is NOT exposed via REST
- Accessed only internally by OrderService
- Prevents overselling and logic bypass

### Order Placement Flow
```
BEGIN TRANSACTION
 → Validate product
 → Reserve inventory
 → Create order
COMMIT
```

### Exception Handling
- Domain-specific exceptions
- Global exception handler using @RestControllerAdvice
- Clean, predictable API error responses

### Test Data Strategy
- Seeded via CommandLineRunner
- Controlled via profile
- Idempotent seeding

### Key Learnings (Phase 1)
- Aggregate roots & invariants
- Transaction boundaries
- Overselling prevention
- Why Inventory has no controller
- Global exception handling

---

## Next Phases
- Phase 2: Security (Spring Security, JWT)
- Phase 3: Kafka & Async Events
- Phase 4: Microservices Decomposition
- Phase 5: Observability & Performance
