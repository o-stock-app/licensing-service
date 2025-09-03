
---

### `licensing-service/README.md`


The **Licensing Service** is part of the o-stock system.  
It manages licenses associated with organizations, including CRUD operations and bootstrapping sample licenses.

---

## 🚀 Endpoints

Base path: `/optima/v1/license`

- `GET /test`  
  Health-check. Returns `"Hello, License service is up and running."`

- `GET /{licenseId}`  
  Fetch a single license by ID (UUID).

- `GET /{licenseId}/{orgId}`  
  Fetch a license for a given organization.

- `POST /`  
  Create a new license.  
  **Body**: `License` JSON  
  **Path Variable**: `orgId` (organization ID)

- `PUT /`  
  Update an existing license.  
  **Body**: `License` JSON  
  **Path Variable**: `organizationId`

- `GET /bootstrap`  
  Fetch bootstrapped license data.

- `DELETE /{licenseId}`  
  Delete a license by ID.  
  **Path Variable**: `orgId` (organization ID)

---

## ⚙️ Tech Stack

- **Language**: Java 21  
- **Framework**: Spring Boot 3.x  
- **Build Tool**: Maven  
- **Database**: PostgreSQL  
- **Containerization**: Docker  

---

## 🏗️ Setup & Run

### Prerequisites
- JDK 21
- Maven
- PostgreSQL (local or containerized)

### Build & Run
```bash
./mvnw clean install
./mvnw spring-boot:run
