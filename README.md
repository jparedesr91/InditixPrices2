# InditixPrices2 - Inditex Brand Price System 🏷️

![Java Version](https://img.shields.io/badge/Java-21%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen)
![Database](https://img.shields.io/badge/Database-H2%2FPostgreSQL-blue)
![License](https://img.shields.io/badge/License-MIT-blue)
![Maven](https://img.shields.io/badge/Build-Maven-3.9.8-red)

---

## Table of Contents 📚

- [Features](#features-)
- [Technologies](#technologies-)
- [Installation](#installation-)
- [API Documentation](#api-documentation-)
- [Use example](#use-example-)

---

## Features ✨

- **RESTful API**
- **Docker-ready** configuration

---

## Technologies 🛠️

- **Core Framework**: Spring Boot 3.4.1
- **Persistence**: Spring Data JPA
- **Database**: H2 (development)
- **API Documentation**: SpringDoc OpenAPI 3
- **Testing**: JUnit, Cucumber, Mockito
- **Utilities**: Lombok, MapStruct
- **Build**: Maven, Docker

---

## Installation 🚀

### Prerequisites

- Java 21 JDK
- Maven 3.8+
- Docker 20.10+ (optional)

1. **Clone repository**:
   ```bash
   git clone https://github.com/jparedesr91/InditixPrices2.git
   cd InditixPrices2

2. **Build application**:
   ```bash
   mvn clean package

3. **Run with Docker**
   ```bash
   mvn docker:build
   mvn docker:run
   
4. **Run locally**
   ```bash
   mvn docker:build
   mvn docker:run

## API Documentation 📡

    Access interactive API docs after starting the application:

    Swagger UI: http://localhost:8080/inditex/prices/swagger-ui

    OpenAPI spec: http://localhost:8080/inditex/prices/v3/api-docs

## Use example
`curl --location 'http://localhost:8080/inditex/prices/filter'
--header 'Content-Type: application/json'
--data '{
    "productId": 35455,
    "brandId": 1,
    "applicationDate": "2020-06-14T16:00:00"
}'`

