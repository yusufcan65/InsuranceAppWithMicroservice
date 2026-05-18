# 🛡️ Insurance Management System — Microservices Architecture

> **TR:** Sigorta poliçelerini, müşterileri, ödemeleri ve araçları yöneten kapsamlı bir mikroservis tabanlı sigorta yönetim sistemi.
>
> **EN:** A comprehensive microservices-based insurance management system handling policies, customers, payments, and vehicles.

---

## 📋 Table of Contents / İçindekiler

- [Overview / Genel Bakış](#overview--genel-bakış)
- [Architecture / Mimari](#architecture--mimari)
- [Services / Servisler](#services--servisler)
- [Tech Stack / Teknoloji Yığını](#tech-stack--teknoloji-yığını)
- [Getting Started / Başlangıç](#getting-started--başlangıç)
- [Environment Variables / Ortam Değişkenleri](#environment-variables--ortam-değişkenleri)
- [API Endpoints](#api-endpoints)
- [Monitoring / İzleme](#monitoring--izleme)
- [Project Structure / Proje Yapısı](#project-structure--proje-yapısı)

---

## Overview / Genel Bakış

**TR:**
Bu proje, sigorta sektörüne yönelik geliştirilmiş bir mikroservis mimarisidir. Kullanıcı yönetimi, müşteri işlemleri, araç kayıtları, poliçe yönetimi ve ödeme akışları birbirinden bağımsız servisler olarak tasarlanmıştır. Spring Cloud ekosistemi üzerine inşa edilmiş olup Docker ile tam containerize edilmiştir.

**EN:**
This project is a microservices architecture built for the insurance sector. User management, customer operations, vehicle records, policy management, and payment flows are designed as independent services. Built on the Spring Cloud ecosystem and fully containerized with Docker.

---

## Architecture / Mimari

```
                        ┌─────────────────┐
                        │  Config Server  │  :8888
                        └────────┬────────┘
                                 │
                        ┌────────▼────────┐
                        │  Eureka Server  │  :8761
                        └────────┬────────┘
                                 │
                        ┌────────▼────────┐
                        │   API Gateway   │  :8083
                        │  (JWT Auth +    │
                        │   Rate Limit)   │
                        └────────┬────────┘
                                 │
              ┌──────────────────┼──────────────────┐
              │                  │                  │
    ┌─────────▼──────┐  ┌───────▼────────┐  ┌──────▼────────┐
    │  Auth Service  │  │  User Service  │  │ Customer Svc  │
    │    :8081       │  │    :8082       │  │    :8084      │
    └────────────────┘  └────────────────┘  └───────────────┘
    ┌────────────────┐  ┌────────────────┐  ┌───────────────┐
    │ Policy Service │  │ Payment Service│  │Vehicle Service│
    │    :8086       │  │    :8091       │  │    :8088      │
    └────────────────┘  └────────────────┘  └───────────────┘
    ┌────────────────┐  ┌────────────────┐  ┌───────────────┐
    │ Health Service │  │  Home Service  │  │Traffic Service│
    │    :8087       │  │    :8085       │  │    :8089      │
    └────────────────┘  └────────────────┘  └───────────────┘
    ┌────────────────┐
    │ Kasko Service  │
    │    :8090       │
    └────────────────┘
```

### Infrastructure / Altyapı

```
┌─────────────────────────────────────────────────────────────┐
│                     INFRASTRUCTURE                          │
│                                                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │ Config Server│  │    Eureka    │  │   API Gateway    │  │
│  │    :8888     │  │    :8761     │  │      :8083       │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
│                                                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │  PostgreSQL  │  │    Redis     │  │     Kafka        │  │
│  │    :5434     │  │    :6379     │  │      :9092       │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
│                                                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │Elasticsearch │  │   Logstash   │  │     Kibana       │  │
│  │    :9200     │  │    :5000     │  │      :5601       │  │
│  └──────────────┘  └──────────────┘  └──────────────────┘  │
│                                                             │
│  ┌──────────────┐  ┌──────────────┐                        │
│  │    Zipkin    │  │  Kafka UI    │                        │
│  │    :9411     │  │    :8098     │                        │
│  └──────────────┘  └──────────────┘                        │
└─────────────────────────────────────────────────────────────┘
```

---

## Services / Servisler

| Service | Port | Description (EN) | Açıklama (TR) |
|---------|------|-------------------|---------------|
| **config-server** | 8888 | Centralized configuration management via Git | Git üzerinden merkezi konfigürasyon yönetimi |
| **eureka-server** | 8761 | Service discovery & registry | Servis keşfi ve kayıt |
| **api-gateway** | 8083 | Unified entry point, JWT auth, rate limiting | Tek giriş noktası, JWT doğrulama, hız sınırlama |
| **auth-service** | 8081 | Authentication & authorization | Kimlik doğrulama ve yetkilendirme |
| **user-service** | 8082 | User profile management | Kullanıcı profil yönetimi |
| **customer-service** | 8084 | Customer records with Redis caching | Redis önbellekli müşteri kayıtları |
| **policy-service** | 8086 | Insurance policy lifecycle | Sigorta poliçesi yaşam döngüsü |
| **payment-service** | 8091 | Payment processing via Kafka | Kafka üzerinden ödeme işlemleri |
| **vehicle-service** | 8088 | Vehicle registry with Redis caching | Redis önbellekli araç kayıtları |
| **health-service** | 8087 | Health insurance management | Sağlık sigortası yönetimi |
| **home-service** | 8085 | Home insurance management | Konut sigortası yönetimi |
| **traffic-service** | 8089 | Traffic insurance (zorunlu trafik) | Zorunlu trafik sigortası |
| **kasko-service** | 8090 | Comprehensive vehicle insurance | Kasko sigortası |

---

## Tech Stack / Teknoloji Yığını

### Backend
| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17 | Core language |
| Spring Boot | 3.x | Application framework |
| Spring Cloud | 2023.x | Microservices infrastructure |
| Spring Cloud Config | — | Centralized config |
| Spring Cloud Eureka | — | Service discovery |
| Spring Cloud Gateway | — | API gateway |
| Spring Security + JWT | — | Authentication |
| Spring Data JPA | — | ORM |
| Spring Kafka | — | Event streaming |
| Spring Data Redis | — | Caching |

### Infrastructure
| Technology | Purpose |
|-----------|---------|
| Docker & Docker Compose | Containerization |
| PostgreSQL 15 | Primary database (per-service DB) |
| Redis | Caching (customer, vehicle) |
| Apache Kafka | Async messaging (policy → payment) |
| Zookeeper | Kafka coordination |

### Observability / Gözlemlenebilirlik
| Technology | Purpose |
|-----------|---------|
| ELK Stack (Elasticsearch, Logstash, Kibana) | Centralized logging |
| Zipkin | Distributed tracing |
| Spring Boot Actuator | Health checks & metrics |
| Kafka UI | Kafka topic monitoring |

---

## Getting Started / Başlangıç

### Prerequisites / Gereksinimler

- **Docker** ≥ 24.x
- **Docker Compose** ≥ 2.x
- **Git**

### 1. Clone the Repository / Repoyu Klonla

```bash
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>
```

### 2. Create Environment File / Ortam Dosyasını Oluştur

```bash
cp .env.example .env
```

Then edit `.env` with your values (see [Environment Variables](#environment-variables--ortam-değişkenleri) section).

### 3. Start All Services / Tüm Servisleri Başlat

```bash
docker compose up --build -d
```

> **TR:** Servisler sıralı olarak ayağa kalkar: Config Server → Eureka → API Gateway → Mikroservisler. Bu işlem ilk çalıştırmada 3–5 dakika sürebilir.
>
> **EN:** Services start in order: Config Server → Eureka → API Gateway → Microservices. First run may take 3–5 minutes.

### 4. Verify / Doğrula

```bash
# Check all containers are running
docker compose ps

# Check Eureka dashboard
open http://localhost:8761

# Check API Gateway
curl http://localhost:8083/actuator/health
```

### 5. Stop Services / Servisleri Durdur

```bash
docker compose down

# To remove volumes (full reset)
docker compose down -v
```

---

## Environment Variables / Ortam Değişkenleri

Create a `.env` file in the project root. **Never commit this file to Git.**

```env
# === DATABASE ===
DB_PORT=5432
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password

# Per-service databases
AUTH_DB=auth_db
USER_DB=user_db
CUSTOMER_DB=customer_db
POLICY_DB=policy_db
PAYMENT_DB=payment_db
VEHICLE_DB=vehicle_db
HEALTH_DB=health_db
HOME_DB=home_db
TRAFFIC_DB=traffic_db
KASKO_DB=kasko_db

# === SECURITY ===
MY_SECRET_KEY=your_jwt_secret_key_min_32_chars

# === CONFIG SERVER (GitHub Config Repo) ===
GITHUB_REPO_URI=https://github.com/<your-username>/<config-repo>.git
GITHUB_USERNAME=your_github_username
GITHUB_TOKEN=your_github_personal_access_token
```

> **TR:** `.env.example` dosyasını template olarak kullanabilirsiniz. Gerçek değerleri asla Git'e commit etmeyin.
>
> **EN:** Use `.env.example` as a template. Never commit real values to Git.

---

## API Endpoints

All requests go through the **API Gateway** at `http://localhost:8083`.

### Authentication

```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "password": "securepassword",
  "role": "ROLE_USER"
}
```

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "johndoe",
  "password": "securepassword"
}
```

> All subsequent requests require: `Authorization: Bearer <token>`

---

### Customer Service `/api/v1/customers`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/customers` | List all customers |
| `GET` | `/api/v1/customers/{id}` | Get customer by ID |
| `POST` | `/api/v1/customers` | Create new customer |
| `PUT` | `/api/v1/customers/{id}` | Update customer |
| `DELETE` | `/api/v1/customers/{id}` | Delete customer |

---

### Policy Service `/api/v1/policies`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/policies` | List all policies |
| `GET` | `/api/v1/policies/{id}` | Get policy by ID |
| `POST` | `/api/v1/policies` | Create new policy |
| `PUT` | `/api/v1/policies/{id}` | Update policy |
| `DELETE` | `/api/v1/policies/{id}` | Cancel policy |

---

### Vehicle Service `/api/v1/vehicles`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/vehicles` | List all vehicles |
| `GET` | `/api/v1/vehicles/{id}` | Get vehicle by ID |
| `POST` | `/api/v1/vehicles` | Register new vehicle |
| `PUT` | `/api/v1/vehicles/{id}` | Update vehicle info |

---

### Insurance Type Endpoints

| Service | Base Path | Description |
|---------|-----------|-------------|
| Health | `/api/v1/health` | Health insurance operations |
| Home | `/api/v1/home` | Home insurance operations |
| Traffic | `/api/v1/traffic` | Mandatory traffic insurance |
| Kasko | `/api/v1/kasko` | Comprehensive vehicle insurance |
| Payment | `/api/v1/payments` | Payment processing |

---

## Monitoring / İzleme

| Tool | URL | Purpose |
|------|-----|---------|
| **Eureka Dashboard** | http://localhost:8761 | Service registry & health |
| **Kibana** | http://localhost:5601 | Centralized log search |
| **Zipkin** | http://localhost:9411 | Distributed request tracing |
| **Kafka UI** | http://localhost:8098 | Kafka topic & message monitoring |

### Actuator Health Checks

Each service exposes health at `/actuator/health`:

```bash
curl http://localhost:8081/actuator/health  # auth-service
curl http://localhost:8086/actuator/health  # policy-service
curl http://localhost:8091/actuator/health  # payment-service
```

---

## Project Structure / Proje Yapısı

```
.
├── config-server/          # Spring Cloud Config Server
├── eureka-server/          # Netflix Eureka Server
├── api-gateway/            # Spring Cloud Gateway
├── authService/            # Authentication & JWT
├── userService/            # User management
├── customerService/        # Customer records (Redis cached)
├── policyService/          # Policy lifecycle + Kafka producer
├── paymentService/         # Payment processing + Kafka consumer
├── vehicleService/         # Vehicle registry (Redis cached)
├── healthService/          # Health insurance
├── homeService/            # Home insurance
├── trafficService/         # Traffic insurance
├── kaskoService/           # Kasko insurance
├── logstash/
│   └── pipeline/           # Logstash pipeline config
├── init.sql                # PostgreSQL DB initialization
├── docker-compose.yml      # Full stack orchestration
├── .env                    # 🔒 Local secrets (gitignored)
└── .env.example            # Template for .env
```

---

## Key Design Decisions / Temel Tasarım Kararları

**TR:**
- **Per-service database:** Her mikroservis kendi PostgreSQL veritabanına sahiptir — veri izolasyonu ve bağımsız ölçekleme için.
- **Async communication:** Policy ve Payment servisleri Kafka üzerinden iletişim kurar — ödeme işlemleri asenkron ve güvenilirdir.
- **Redis caching:** Customer ve Vehicle servisleri sık erişilen verileri Redis'te önbelleğe alır.
- **Centralized config:** Tüm servisler yapılandırmalarını Git tabanlı Config Server'dan alır.
- **Startup ordering:** Docker Compose healthcheck'leri sayesinde servisler doğru sırada ayağa kalkar.

**EN:**
- **Per-service database:** Each microservice owns its PostgreSQL database — for data isolation and independent scaling.
- **Async communication:** Policy and Payment services communicate via Kafka — payment processing is asynchronous and reliable.
- **Redis caching:** Customer and Vehicle services cache frequently accessed data in Redis.
- **Centralized config:** All services fetch their configuration from a Git-backed Config Server.
- **Startup ordering:** Docker Compose healthchecks ensure services start in the correct dependency order.

---

<div align="center">
  <sub>Built with ☕ Java & Spring Cloud</sub>
</div>
