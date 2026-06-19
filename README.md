# Online Bookstore - Backend

## Overview

This project is the backend service for the Online Bookstore application.

It provides REST APIs for:

* User Registration
* User Login
* JWT Authentication
* Book Catalog
* Shopping Cart Management
* Order Checkout

## Technology Stack

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* H2 Database
* Maven

## Prerequisites

* Java 17+
* Maven 3.9+

## Clone Repository

```bash
git clone https://github.com/abhishekg1981992/online-bookstore.git
cd bookstore
```

## Run Application

```bash
mvn clean install

mvn spring-boot:run
```

The application starts on:

http://localhost:8080

## H2 Database Console

http://localhost:8080/h2-console

Connection Settings:

```text
JDBC URL: jdbc:h2:file:./data/bookstoredb
Username: sa
Password:
```

## API Endpoints

### Authentication

#### Register

POST /api/auth/register

```json
{
  "username": "testuser",
  "password": "password123"
}
```

#### Login

POST /api/auth/login

```json
{
  "username": "testuser",
  "password": "password123"
}
```

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

### Books

GET /api/books

### Cart

GET /api/cart

POST /api/cart/items

PUT /api/cart/items/{id}

DELETE /api/cart/items/{id}

### Orders

POST /api/orders

## Features Implemented

* JWT Authentication
* Password Encryption using BCrypt
* Book Catalog
* Shopping Cart
* Checkout and Order Creation
* H2 Database Persistence

## Frontend Repository

The frontend application is available at:

https://github.com/abhishekg1981992/bookstore-ui.git
