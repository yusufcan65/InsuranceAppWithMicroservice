# 🛡️ Insurance Management System

<div align="center">

## Mikroservis Mimarisi ile Geliştirilmiş Sigorta Yönetim Platformu

Spring Cloud ekosistemi kullanılarak geliştirilen,  
yüksek erişilebilirlik, merkezi yönetim, gözlemlenebilirlik ve dağıtık sistem prensiplerine uygun modern sigorta yönetim sistemi.

<img src="https://img.shields.io/badge/Java-17-red?style=for-the-badge" />
<img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge" />
<img src="https://img.shields.io/badge/Microservice-Architecture-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge" />
<img src="https://img.shields.io/badge/Kafka-Event%20Driven-black?style=for-the-badge" />
<img src="https://img.shields.io/badge/Redis-Caching-red?style=for-the-badge" />
<img src="https://img.shields.io/badge/ELK-Logging-orange?style=for-the-badge" />

</div>

---

# 📌 Proje Hakkında

Bu proje, sigorta şirketleri ve acentelerin ihtiyaçlarına yönelik geliştirilmiş kapsamlı bir **mikroservis tabanlı sigorta yönetim platformudur**.

Sistem üzerinden:

- 👤 Kullanıcı yönetimi
- 🧾 Müşteri kayıt işlemleri
- 🚗 Araç yönetimi
- 📑 Teklif oluşturma
- 🛡️ Poliçe oluşturma
- 💳 Ödeme işlemleri
- 🔄 Poliçeleştirme süreçleri
- ⚡ Event-driven servis haberleşmesi
- 📦 Merkezi konfigürasyon yönetimi
- 📊 Dağıtık loglama ve tracing

gibi birçok işlem gerçekleştirilmektedir.

---

# 🏗️ Kullanılan Mimari

Bu proje tamamen:

- **Microservice Architecture**
- **Event Driven Architecture**
- **Distributed System Design**
- **Centralized Configuration**
- **API Gateway Pattern**
- **Saga Pattern**
- **Database Per Service Pattern**

yaklaşımları kullanılarak geliştirilmiştir.

---

# 🧠 Sistem Mimarisi

```text
                                      ┌──────────────────────┐
                                      │    Config Server     │
                                      │        :8888         │
                                      └──────────┬───────────┘
                                                 │
                                      ┌──────────▼───────────┐
                                      │    Eureka Server     │
                                      │        :8761         │
                                      └──────────┬───────────┘
                                                 │
                                      ┌──────────▼───────────┐
                                      │     API Gateway      │
                                      │        :8083         │
                                      │ JWT + Rate Limiting  │
                                      └──────────┬───────────┘
                                                 │
                ┌────────────────────────────────┼────────────────────────────────┐
                │                                │                                │
      ┌─────────▼─────────┐          ┌──────────▼──────────┐          ┌──────────▼──────────┐
      │   Auth Service    │          │    User Service     │          │  Customer Service   │
      │       :8081       │          │       :8082         │          │       :8084         │
      └───────────────────┘          └─────────────────────┘          └─────────────────────┘

      ┌───────────────────┐          ┌─────────────────────┐          ┌─────────────────────┐
      │  Policy Service   │◄────────►│  Payment Service    │          │  Vehicle Service    │
      │       :8086       │  Kafka   │       :8091         │          │       :8088         │
      └───────────────────┘          └─────────────────────┘          └─────────────────────┘

      ┌───────────────────┐          ┌─────────────────────┐          ┌─────────────────────┐
      │  Health Service   │          │   Home Service      │          │ Traffic Service     │
      │       :8087       │          │       :8085         │          │       :8089         │
      └───────────────────┘          └─────────────────────┘          └─────────────────────┘

                                      ┌─────────────────────┐
                                      │    Kasko Service    │
                                      │       :8090         │
                                      └─────────────────────┘


⚙️ Altyapı ve DevOps Yapısı

┌────────────────────────────────────────────────────────────────────────────┐
│                             INFRASTRUCTURE                                 │
├────────────────────────────────────────────────────────────────────────────┤
│ PostgreSQL  • Redis • Kafka • Zipkin • ELK • Docker • Eureka • Gateway     │
└────────────────────────────────────────────────────────────────────────────┘


🚀 Kullanılan Teknolojiler
☕ Backend Teknolojileri

| Teknoloji            | Açıklama                       |
| -------------------- | ------------------------------ |
| Java 17              | Ana programlama dili           |
| Spring Boot          | Mikroservis geliştirme         |
| Spring Security      | Authentication & Authorization |
| Spring Data JPA      | ORM yapısı                     |
| Spring Cloud         | Mikroservis altyapısı          |
| Spring Cloud Gateway | API Gateway                    |
| Eureka Server        | Service Discovery              |
| Config Server        | Merkezi konfigürasyon          |
| OpenFeign            | Servisler arası iletişim       |
| Resilience4j         | Circuit Breaker & Retry        |
| Kafka                | Event Driven Communication     |
| Redis                | Cache mekanizması              |
| PostgreSQL           | Veritabanı                     |
| Docker               | Containerization               |
| Zipkin               | Distributed Tracing            |
| ELK Stack            | Merkezi log yönetimi           |
| Swagger/OpenAPI      | API dokümantasyonu             |

🧩 Mikroservisler

| Servis           | Açıklama                     |
| ---------------- | ---------------------------- |
| auth-service     | JWT authentication işlemleri |
| user-service     | Kullanıcı yönetimi           |
| customer-service | Müşteri işlemleri            |
| policy-service   | Poliçe yönetimi              |
| payment-service  | Ödeme işlemleri              |
| vehicle-service  | Araç işlemleri               |
| traffic-service  | Trafik sigortası             |
| kasko-service    | Kasko işlemleri              |
| home-service     | Konut sigortası              |
| health-service   | Sağlık sigortası             |
| api-gateway      | Merkezi giriş noktası        |
| eureka-server    | Service discovery            |
| config-server    | Merkezi config yönetimi      |

📦 Ortak Yapılar

Proje içerisinde ayrıca:
insuranceCommon

modülü bulunmaktadır.

Bu modül içerisinde:

BaseEntity
Kafka Event Yapıları
Ortak DTO’lar
Exception Yapıları
Constants
Shared Response Yapıları

gibi tüm ortak sistem bileşenleri bulunmaktadır.

🔐 Güvenlik Yapısı

Sistemde JWT tabanlı authentication mekanizması kullanılmaktadır.

Authentication işlemleri:
auth-service
üzerinden gerçekleştirilmektedir.

Tüm istekler önce:
api-gateway
üzerinden geçmektedir.

Gateway tarafında:

JWT doğrulama
Request filtering
Merkezi güvenlik kontrolü
Rate limiting

işlemleri yapılmaktadır.

⚡ Rate Limiting

API Gateway üzerinde Redis tabanlı rate limiting sistemi bulunmaktadır.

Aynı kullanıcı/IP üzerinden çok fazla istek gelmesi durumunda sistem:

HTTP 429 TOO MANY REQUESTS

döndürmektedir.

Bu yapı sayesinde:

DDOS benzeri yüklerin azaltılması
Gateway korunması
Sistem stabilitesinin artırılması

🚀 Cache Mekanizması

Redis cache kullanımı:
amaçlanmıştır.

| Servis           | Amaç                          |
| ---------------- | ----------------------------- |
| customer-service | Sık erişilen müşteri verileri |
| vehicle-service  | Araç verilerinin cachelenmesi |

Amaç:

Daha hızlı response süreleri
Veritabanı yükünün azaltılması
Performans optimizasyonu

📡 Kafka Event Driven Yapısı

Servisler arası haberleşmede Kafka kullanılmaktadır.

Kullanılan eventler:
PolicyActivatedEvent
PolicyCreatedEvent
PolicyDeleteEvent
PaymentCompletedEvent
PaymentFailedEvent

Bu yapı sayesinde servisler:

Loose coupling
Asenkron iletişim
Daha ölçeklenebilir yapı
Daha güvenli transaction yönetimi

özelliklerine sahip olmuştur.

🔄 Saga Pattern Kullanımı

Projede distributed transaction yönetimi için Saga Pattern kullanılmıştır.

Ödeme Başarılı Senaryosu
1. Policy oluşturulur
2. Payment işlemi başlatılır
3. Ödeme başarılı olursa:
   -> Policy ACTIVE olur
   -> Payment COMPLETED olur

Ödeme Başarısız Senaryosu
1. Payment başarısız olur
2. PaymentFailedEvent yayınlanır
3. Policy tekrar PASIF hale getirilir
4. Payment status FAILED yapılır

Bu yapı sayesinde:

Veri tutarlılığı korunur
Distributed transaction problemi çözülür
Sistem rollback mekanizmasına sahip olur

📥 Projection Yapısı

Payment servisi içerisinde:
policyProjection
tablosu bulunmaktadır.

Bu yapı sayesinde payment servisi:

Policy verilerini local olarak tutabilmektedir
Cross-service DB erişimine ihtiyaç duymaz
Daha bağımsız çalışır

Projection güncellemeleri Kafka eventleri ile yapılmaktadır.

📊 Gözlemlenebilirlik (Observability)

Projede production seviyesinde monitoring altyapısı bulunmaktadır.

🔍 Zipkin

Distributed tracing için kullanılmaktadır.

İsteklerin servisler arasındaki akışı izlenebilir.
http://localhost:9411

📈 ELK Stack

Merkezi log yönetimi için:

Elasticsearch
Logstash
Kibana

kullanılmıştır.

Kibana:
http://localhost:5601

📨 Kafka UI

Kafka topiclerini görüntülemek için:
http://localhost:8098

🌐 Swagger API Documentation

Tüm servis endpointleri API Gateway üzerinden erişilebilir durumdadır.

Swagger UI:
http://localhost:8083/swagger-ui.html

Endpointler
Authentication

Customer Service
GET    /api/v1/customers
GET    /api/v1/customers/{id}
POST   /api/v1/customers
PUT    /api/v1/customers/{id}
DELETE /api/v1/customers/{id}
Policy Service
GET    /api/v1/policies
POST   /api/v1/policies
PUT    /api/v1/policies/{id}
DELETE /api/v1/policies/{id}
Payment Service
POST /api/v1/payments
