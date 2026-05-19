# 🛡️ Sigorta Yönetim Platformu (Insurance Management System)

Kritik sigortacılık operasyonlarını modern yazılım mimarisi paternleri ile yöneten; yüksek erişilebilirlik (HA), veri izolasyonu ve asenkron veri tutarlılığı (Eventual Consistency) ilkeleri üzerine inşa edilmiş **dağıtık ve ölçeklenebilir bir enterprise backend platformudur**.

Sistem, monolitik bir yapının mikroservis mimarisine dönüştürülmesiyle; bağımsız veri tabanları (Database-per-Service), olay güdümlü asenkron mesajlaşma (Event-Driven), merkezi izleme ve gözlemlenebilirlik (Observability) stratejileri uygulanarak modernleştirilmiştir.

---

### 🏛️ Mimari Odak Noktaları
* **Kurumsal Arka Plan Mimarisi (Enterprise Backend Architecture)**
* **Dağıtık Sistem Yönetimi (Distributed Systems Orchestration)**
* **Olay Güdümlü Tasarım (Event-Driven Design & Saga Pattern)**

---

### 💻 Çekirdek Teknoloji Matrisi

| Katman | Teknoloji Bileşenleri | Çözüm Sağlanan Mimari Patern |
| :--- | :--- | :--- |
| **Ana Çerçeve** | Java, Spring Boot, Spring Cloud | Mikroservis Altyapısı & Servis Keşfi (Discovery) |
| **Veri & Önbellek** | PostgreSQL, Redis | Veri İzolasyonu (Database-per-Service) & Performans |
| **Mesajlaşma** | Apache Kafka, Zookeeper | Olay Güdümlü İletişim (Event-Driven) & Saga Yönetimi |
| **Gözlemlenebilirlik** | ELK Stack (Elasticsearch, Logstash, Kibana), Zipkin | Dağıtık İzleme (Distributed Tracing) & Merkezi Loglama |
| **Altyapı** | Docker, Docker Compose | Konteynerleştirme & Dağıtım Orkestrasyonu |

---

# 📌 Proje Hakkında

Insurance Management System, sigorta şirketleri ve acentelerin ihtiyaçları doğrultusunda geliştirilmiş modern bir mikroservis mimarisi uygulamasıdır.

Bu proje içerisinde:

- 👤 Kullanıcı yönetimi
- 👥 Müşteri yönetimi
- 🚗 Araç yönetimi
- 📄 Poliçe oluşturma
- 💳 Ödeme işlemleri
- 🔄 Saga Pattern ile distributed transaction yönetimi
- ⚡ Event-driven communication
- 🧠 Redis cache yönetimi
- 🚦 API Gateway & Rate Limiting
- 🔍 Merkezi loglama
- 📊 Distributed tracing
- ☁️ Merkezi konfigürasyon yönetimi
- 🐳 Docker orchestration

gibi gerçek dünya enterprise backend konseptleri uygulanmıştır.

---

# 🎯 Projenin Temel Amacı

Bu proje yalnızca CRUD işlemleri yapan klasik bir backend uygulaması değildir.

Amaç:

- Gerçek bir mikroservis mimarisi oluşturmak
- Spring Cloud ekosistemini aktif kullanmak
- Distributed system mantığını uygulamak
- Event-driven architecture geliştirmek
- Saga Pattern implement etmek
- Merkezi loglama ve tracing sistemi kurmak
- Docker tabanlı production benzeri ortam hazırlamak
- Enterprise backend geliştirme yaklaşımını uygulamak

olmuştur.

---

# 🏗️ Mikroservis Mimarisi

                            ```text
                                                                  ┌────────────────────┐
                                                                  │   Config Server    │
                                                                  │       :8888        │
                                                                  └─────────┬──────────┘
                                                                            │
                                                                  ┌─────────▼──────────┐
                                                                  │   Eureka Server    │
                                                                  │       :8761        │
                                                                  └─────────┬──────────┘
                                                                            │
                                                                  ┌─────────▼──────────┐
                                                                  │    API Gateway     │
                                                                  │       :8083        │
                                                                  │ JWT + Rate Limit   │
                                                                  └─────────┬──────────┘
                                                                            │
                                        ┌───────────────────────────────────┼───────────────────────────────────┐
                                        │                                   │                                   │
                            
                             ┌──────────▼──────────┐            ┌──────────▼──────────┐            ┌──────────▼──────────┐
                             │    Auth Service     │            │    User Service     │            │  Customer Service   │
                             │        :8081        │            │        :8082        │            │        :8084        │
                             └─────────────────────┘            └─────────────────────┘            └─────────────────────┘
                            
                             ┌─────────────────────┐            ┌─────────────────────┐            ┌─────────────────────┐
                             │   Policy Service    │◄──────────►│   Payment Service   │            │   Vehicle Service   │
                             │        :8086        │   Kafka    │        :8091        │            │        :8088        │
                             └─────────────────────┘            └─────────────────────┘            └─────────────────────┘
                            
                             ┌─────────────────────┐            ┌─────────────────────┐            ┌─────────────────────┐
                             │   Health Service    │            │    Home Service     │            │  Traffic Service    │
                             │        :8087        │            │        :8085        │            │        :8089        │
                             └─────────────────────┘            └─────────────────────┘            └─────────────────────┘
                            
                                                                  ┌────────────────────┐
                                                                  │   Kasko Service    │
                                                                  │       :8090        │
                                                                  └────────────────────┘
                            ```

---

# ☁️ Infrastructure Mimarisi

                                      ```text
                                      ┌──────────────────────────────────────────────────────────────────────────────┐
                                      │                             INFRASTRUCTURE                                   │
                                      │                                                                              │
                                      │  ┌────────────┐   ┌────────────┐   ┌────────────────────┐                    │
                                      │  │ PostgreSQL │   │   Redis    │   │       Kafka        │                    │
                                      │  │   :5434    │   │   :6379    │   │       :9092        │                    │
                                      │  └────────────┘   └────────────┘   └────────────────────┘                    │
                                      │                                                                              │
                                      │  ┌────────────┐   ┌─────────────┐   ┌──────────────────┐                     │
                                      │  │   Zipkin   │   │Elasticsearch│   │      Kibana      │                     │
                                      │  │   :9411    │   │    :9200    │   │       :5601      │                     │
                                      │  └────────────┘   └─────────────┘   └──────────────────┘                     │
                                      │                                                                              │
                                      │  ┌────────────┐   ┌────────────┐                                             │
                                      │  │  Logstash  │   │  Kafka UI  │                                             │
                                      │  │   :5000    │   │   :8098    │                                             │
                                      │  └────────────┘   └────────────┘                                             │
                                      │                                                                              │
                                      └──────────────────────────────────────────────────────────────────────────────┘
                                      ```

---

# 📦 Mikroservisler

| Servis | Port | Açıklama |
|---|---|---|
| config-server | 8888 | Merkezi konfigürasyon yönetimi |
| eureka-server | 8761 | Service Discovery |
| api-gateway | 8083 | Merkezi giriş noktası |
| auth-service | 8081 | Authentication & JWT işlemleri |
| user-service | 8082 | Kullanıcı yönetimi |
| customer-service | 8084 | Müşteri yönetimi |
| policy-service | 8086 | Poliçe yönetimi |
| payment-service | 8091 | Ödeme işlemleri |
| vehicle-service | 8088 | Araç yönetimi |
| health-service | 8087 | Sağlık sigortası işlemleri |
| home-service | 8085 | Konut sigortası işlemleri |
| traffic-service | 8089 | Trafik sigortası işlemleri |
| kasko-service | 8090 | Kasko sigortası işlemleri |
| insuranceCommon | - | Ortak entity ve utility yapıları |

---

# 🧱 Kullanılan Teknolojiler

## Backend Teknolojileri

| Teknoloji | Açıklama |
|---|---|
| Java 17 | Ana programlama dili |
| Spring Boot | Mikroservis geliştirme |
| Spring Security | Authentication & Authorization |
| JWT | Token bazlı güvenlik |
| Spring Data JPA | ORM |
| Hibernate | Database yönetimi |
| Spring Cloud | Mikroservis altyapısı |
| OpenFeign | Servisler arası iletişim |
| Resilience4j | Fault tolerance |
| Kafka | Event-driven architecture |
| Redis | Cache yönetimi |
| PostgreSQL | Veritabanı |
| Docker | Containerization |

---

# ☁️ Spring Cloud Bileşenleri

| Yapı | Amaç |
|---|---|
| Config Server | Merkezi konfigürasyon |
| Eureka Server | Servis keşfi |
| API Gateway | Merkezi giriş noktası |
| OpenFeign | Declarative REST Client |
| Spring Cloud Gateway | Routing & Filtering |

---

# 🔄 Saga Pattern Yapısı

Sistem içerisinde distributed transaction yönetimi için Saga Pattern kullanılmıştır.

## İşleyiş

### 1️⃣ Poliçe Oluşturma

- Policy Service yeni poliçe oluşturur
- `PolicyCreatedEvent` Kafka üzerinden publish edilir

### 2️⃣ Payment Projection

Payment Service kendi tarafında:

- `policyProjection` tablosu oluşturur
- Policy bilgilerini burada tutar
- Böylece Payment Service policy datasına bağımlı olmadan çalışabilir

### 3️⃣ Ödeme Başarılı Durumu

Başarılı ödeme sonrasında:

- `PaymentCompletedEvent` publish edilir
- Poliçe ACTIVE durumuna geçirilir
- Transaction başarıyla tamamlanır

### 4️⃣ Ödeme Başarısız Durumu

Başarısız ödeme durumunda:

- `PaymentFailedEvent` publish edilir
- Policy tekrar pasif hale getirilir
- Payment status FAILED olur
- Distributed rollback gerçekleştirilir

---

# ⚡ Kullanılan Eventler

```text
PolicyCreatedEvent
PolicyActivatedEvent
PolicyDeleteEvent
PaymentCompletedEvent
PaymentFailedEvent
```

---

# 🧠 Redis Cache Kullanımı

Redis cache aşağıdaki servislerde kullanılmaktadır:

| Servis | Kullanım Amacı |
|---|---|
| customer-service | Müşteri verilerini cachelemek |
| vehicle-service | Araç verilerini cachelemek |

## Kazanımlar

- Daha hızlı response süresi
- Database yükünün azalması
- Performans optimizasyonu

---

# 🚦 API Gateway Özellikleri

API Gateway üzerinde:

- JWT doğrulama
- Merkezi filter yapısı
- Request routing
- Swagger aggregation
- Rate limiting
- Request filtering
- Security kontrolü

yapıları bulunmaktadır.

---

# ⛔ Rate Limiting

Gateway üzerinde Redis tabanlı rate limiting uygulanmıştır.

Aynı istemciden çok fazla request gelmesi durumunda:

```http
HTTP 429 TOO MANY REQUESTS
```

dönülmektedir.

Bu yapı sistemin korunmasını sağlar.

---

# 🔐 JWT Güvenlik Yapısı

Sistem içerisinde:

- Access Token yapısı
- Spring Security
- Gateway level authentication
- Merkezi authorization

mekanizmaları kullanılmaktadır.

JWT işlemleri:

- auth-service içerisinde üretilir
- api-gateway üzerinde validate edilir

---

# 🔍 Merkezi Loglama (ELK)

Sistem logları:

```text
Service -> Logstash -> Elasticsearch -> Kibana
```

akışı ile yönetilmektedir.

## Kullanılan Yapılar

| Teknoloji | Amaç |
|---|---|
| Elasticsearch | Log storage |
| Logstash | Log pipeline |
| Kibana | Log visualization |

---

# 📊 Distributed Tracing (Zipkin)

Servisler arası request akışı Zipkin ile takip edilmektedir.

Böylece:

- Request lifecycle
- Servisler arası geçişler
- Performans analizi
- Bottleneck tespiti

kolayca yapılabilmektedir.

---

# 🐳 Docker Yapısı

Tüm sistem Docker Compose ile ayağa kaldırılmaktadır.

## İçerdiği Servisler

- PostgreSQL
- Redis
- Kafka
- Zookeeper
- Zipkin
- Elasticsearch
- Logstash
- Kibana
- Kafka UI
- Tüm mikroservisler

---

# 🚀 Projeyi Çalıştırma

## Gereksinimler

- Docker
- Docker Compose
- Git

---

## Repoyu Klonla

```bash
git clone https://github.com/yusufcan65/InsuranceAppWithMicroservice.git
```

```bash
cd InsuranceAppWithMicroservice
```

---

## Ortam Değişkenlerini Ayarla

`.env` dosyası oluştur:

```env

# === VERİTABANI ===
DB_PORT=5432
DB_USERNAME=postgres
DB_PASSWORD=postgres

# Servis başına veritabanı isimleri
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

# === GÜVENLİK ===
MY_SECRET_KEY=your_jwt_secret_key_minimum_32_characters

# === CONFIG SERVER (GitHub Config Repo) ===
GITHUB_REPO_URI=https://github.com/<kullanici>/<config-repo>.git
GITHUB_USERNAME=your_github_username
GITHUB_TOKEN=your_github_personal_access_token
```
##  🗄️ Veri Tabanı Şeması ayarla

`init.sql` dosyası oluştur:
---
````
CREATE DATABASE auth_db;
CREATE DATABASE user_db;
CREATE DATABASE customer_db;
CREATE DATABASE health_db;
CREATE DATABASE home_db;
CREATE DATABASE kasko_db;
CREATE DATABASE traffic_db;
CREATE DATABASE policy_db;
CREATE DATABASE payment_db;
CREATE DATABASE vehicle_db;
````
## Sistemi Başlatma yapısı

```bash
docker compose up --build -d config-server
docker compose up --build -d eureka-server
docker compose up --build -d
```

---

# 🌐 Servis URL'leri

| Servis | URL |
|---|---|
| API Gateway | http://localhost:8083 |
| Eureka | http://localhost:8761 |
| Zipkin | http://localhost:9411 |
| Kibana | http://localhost:5601 |
| Kafka UI | http://localhost:8098 |

---

# 🔐 Authentication & İlk Kullanıcı Oluşturma Mimarisi

Sistem içerisinde authentication domaini ile business kullanıcı domaini birbirinden ayrılmıştır.

Bu nedenle:

- `auth-service` yalnızca authentication işlemlerinden sorumludur
- `user-service` ise business kullanıcı yönetimini gerçekleştirir

## İlk Kullanıcı Oluşturma

`user-service` endpointleri JWT ile korunduğu için sistem ilk ayağa kalktığında doğrudan `user-service` üzerinden kullanıcı oluşturulamaz.

Bu problemi çözmek amacıyla sistemde minimal seviyede public authentication endpointleri bırakılmıştır.

İlk kullanıcı oluşturma akışı:

      ```bash
      Client
         ↓
      auth-service
         ↓
      AuthUser Create
      ```
Bu işlem sonrasında kullanıcı login olarak JWT token elde eder ve artık korumalı user-service endpointlerine erişebilir.

Business Kullanıcı Oluşturma Akışı

Gerçek kullanıcı yönetimi ise user-service üzerinden gerçekleştirilir.

Bu süreçte:

    ```bash
    Client
        ↓
    UserRequest
        ↓
    user-service
        ↓
    Feign Client
        ↓
    auth-service
        ↓
    AuthUser Create
    ```
akışı çalışır.

Bu yapı sayesinde:

Authentication ve business domainleri ayrıştırılmıştır
Security yapısı merkezi hale getirilmiştir
Mikroservis sorumlulukları net şekilde ayrılmıştır
Feign Client ile servisler arası kontrollü iletişim sağlanmıştır

# 📘 Swagger Endpointleri

Tüm swagger endpointleri API Gateway üzerinden erişilebilir durumdadır.

## Swagger UI

```
http://localhost:8083/swagger-ui.html
```

---

# 📡 Örnek Endpointler

## Authentication

### Register

```http
POST /api/v1/users
```

### Login

```http
POST /api/v1/auth/login
```

---
## User Service

```http
GET    /api/v1/users
POST   /api/v1/users
PUT    /api/v1/users/{id}
```

## Customer Service

```http
GET    /api/v1/customers
POST   /api/v1/customers
PUT    /api/v1/customers/{id}
DELETE /api/v1/customers/{id}
```

---

## Policy Service

```http
GET    /api/v1/policies
POST   /api/v1/policies
PUT    /api/v1/policies/{id}
DELETE /api/v1/policies/{id}
```

---
## Payment Service

```http
GET    /api/v1/payment
```
---

## Policy-Created Services

```http
POST   /api/v1/home-policies
POST   /api/v1/health-policies
POST   /api/v1/kasko-policies
POST   /api/v1/traffic-policies
```
---

## Vehicle Service

```http
GET    /api/v1/vehicles
POST   /api/v1/vehicles
```

---

# 📁 Proje Yapısı

```text
.
├── Microservices
│   ├── api-gateway
│   ├── authService
│   ├── customerService
│   ├── paymentService
│   ├── policyService
│   ├── userService
│   ├── vehicleService
│   ├── healthService
│   ├── homeService
│   ├── trafficService
│   └── kaskoService
│
├── Infrastructure
│   ├── config-server
│   ├── eureka-server
│   └── docker-compose.yml
│
├── Shared
│   └── insuranceCommon
│
├── Database
│   └── init.sql
│
└── Environment
    └── .env
```

---

# 📈 Projede Kullanılan Modern Backend Yaklaşımları

✅ Mikroservis mimarisi  
✅ Distributed systems  
✅ Event-driven architecture  
✅ Saga Pattern  
✅ Distributed transaction management  
✅ API Gateway pattern  
✅ Service Discovery  
✅ Centralized configuration  
✅ Centralized logging  
✅ Distributed tracing  
✅ Cache management  
✅ Rate limiting  
✅ Docker orchestration  
✅ Fault tolerance  
✅ Resilience4j  
✅ JWT authentication  
✅ Redis cache  
✅ Kafka messaging

---

# 💡 Neden Bu Proje Önemli?

Bu proje:

- Sadece CRUD yapan klasik backend projelerinden farklıdır
- Gerçek enterprise mimari yaklaşımı içerir
- Modern Java backend konseptlerini uygular
- Dağıtık sistem problemlerini çözmeye odaklanır
- Production benzeri altyapı sunar

Özellikle:

- Spring Cloud
- Kafka
- Saga Pattern
- ELK
- Zipkin
- Redis
- API Gateway

gibi enterprise seviyede kullanılan teknolojilerin birlikte nasıl çalıştığını göstermektedir.

---

# 👨‍💻 Geliştirici

## Yusuf Can
