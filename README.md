# VaultCore – FinTech Backend API

VaultCore is a secure backend system for a digital banking platform built using **Spring Boot**, **JWT authentication**, and **PostgreSQL**.

## 🚀 Features
- User registration & login with JWT
- One bank account per user
- Deposit & withdraw funds
- Transaction history per user
- Role-based security
- Global exception handling
- RESTful API design

## 🛠 Tech Stack
- Java 21
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger (OpenAPI)

## 🔐 Authentication Flow
1. User logs in → receives JWT
2. JWT sent in `Authorization: Bearer <token>`
3. All protected APIs validate JWT via filter

## 📌 API Endpoints

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### Accounts
- `POST /api/accounts`
- `GET /api/accounts/me`
- `POST /api/accounts/deposit?amount=`
- `POST /api/accounts/withdraw?amount=`

### Transactions
- `GET /api/transactions/me`

## 📖 API Docs
Swagger UI:
