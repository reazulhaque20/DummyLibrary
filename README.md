# Dummy Library

Key Highlights:
The Hexagon (Core)

Domain entities (Library, Aisle, Book) contain business logic
Technology-independent - pure business rules

Ports (Interfaces)

Input Ports (LibraryService) - define what the app can do
Output Ports (Repositories) - define what the app needs

Adapters (Implementations)

Input Adapters (REST Controller) - connect HTTP to business logic
Output Adapters (Spring Data JPA) - connect business logic to database

Benefits You Get:

* Testability - Mock repositories, test business logic in isolation

* Flexibility - Swap REST for GraphQL without touching business code

* Maintainability - Business logic in one place

* Technology Independence - Domain doesn't know about Spring, H2, or REST

## To run this app

```shell
mvn spring-boot:run
```

## Application will run on 8181 port but actuator will run on 8182 port

## Access Swagger-API Doc
http://localhost:8181/swagger-ui.html

## Access H2 Console
http://localhost:8181/h2-console

# Hexagonal Architecture Layers
```
┌─────────────────────────────────────────────────────────────┐
│                    ADAPTERS (Outside)                       │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              INPUT ADAPTERS                           │  │
│  │  - REST Controller (LibraryController)               │  │
│  │  - Handles HTTP requests                             │  │
│  │  - Converts JSON to DTOs                             │  │
│  └──────────────────────────────────────────────────────┘  │
│                           ↓                                  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              PORTS (Interfaces)                       │  │
│  │  INPUT PORT:  LibraryService interface               │  │
│  └──────────────────────────────────────────────────────┘  │
│                           ↓                                  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │         APPLICATION LAYER (Use Cases)                 │  │
│  │  - LibraryServiceImpl (business logic)               │  │
│  │  - DTOs (data transfer objects)                      │  │
│  └──────────────────────────────────────────────────────┘  │
│                           ↓                                  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │         DOMAIN LAYER (Core - The Hexagon)            │  │
│  │  - Entities: Library, Aisle, Book                    │  │
│  │  - Business rules and domain logic                   │  │
│  │  - Pure Java objects (no framework dependencies)     │  │
│  └──────────────────────────────────────────────────────┘  │
│                           ↓                                  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              PORTS (Interfaces)                       │  │
│  │  OUTPUT PORTS: Repository interfaces                 │  │
│  └──────────────────────────────────────────────────────┘  │
│                           ↓                                  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              OUTPUT ADAPTERS                          │  │
│  │  - JPA Repositories (Spring Data)                    │  │
│  │  - Connects to H2 Database                           │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Dependency Flow
```
External Request
       ↓
[INPUT ADAPTER: Controller]
       ↓
[INPUT PORT: Service Interface]
       ↓
[APPLICATION: Service Impl]
       ↓
[DOMAIN: Entities]
       ↓
[OUTPUT PORT: Repository Interface]
       ↓
[OUTPUT ADAPTER: Spring Data JPA]
       ↓
Database (H2)
```

## Layer Mapping to Package Structure
```
com.ctos.dummy.library/
│
├── adapter/
│   └── input/
│       └── rest/
│           └── LibraryController.java        ← INPUT ADAPTER
│
├── domain/
│   ├── model/
│   │   ├── Library.java                      ← DOMAIN CORE
│   │   ├── Aisle.java                        ← DOMAIN CORE
│   │   └── Book.java                         ← DOMAIN CORE
│   │
│   └── port/
│       ├── input/
│       │   └── LibraryService.java           ← INPUT PORT
│       │
│       └── output/
│           ├── LibraryRepository.java        ← OUTPUT PORT
│           ├── AisleRepository.java          ← OUTPUT PORT
│           └── BookRepository.java           ← OUTPUT PORT
│
└── application/
    ├── dto/
    │   ├── LibraryDTO.java                   ← DTOs
    │   ├── AisleDTO.java                     ← DTOs
    │   └── BookDTO.java                      ← DTOs
    │
    └── service/
        └── LibraryServiceImpl.java           ← APPLICATION LAYER

[Spring Data JPA Implementation]              ← OUTPUT ADAPTER (auto-generated)
```

## Improvement

1. Better exception handling
2. Security
3. Cloud Gateway
4. Config Server