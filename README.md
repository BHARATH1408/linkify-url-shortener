# 🔗 Linkify – URL Shortener API

*A sleek and modern backend service built with Spring Boot and MySQL that shortens URLs, manages link expiry, and provides Swagger-based API documentation.*

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge"/>
</p>

---

## 🚀 Features
✅ Shorten long URLs  
✅ Set custom expiration for links  
✅ Redirect with HTTP `302 Found`  
✅ Handle expired (`410 Gone`) & invalid (`404 Not Found`) links  
✅ Swagger UI for easy API testing  
✅ JUnit + MockMvc test coverage

---

## 🧰 Tech Stack

| Layer | Technology |
|:------|:------------|
| Backend | Java 17, Spring Boot 3 |
| Database | MySQL 8, Spring Data JPA, Hibernate |
| Build Tool | Maven |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Testing | JUnit 5, MockMvc |

---

## 🖥️ Swagger UI Preview

🌐 **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

| 🧩 Create Short URL | 🔁 Redirect URL |
|:--:|:--:|
| ![Swagger Screenshot 1](https://user-images.githubusercontent.com/0000000/placeholder1.png) | ![Swagger Screenshot 2](https://user-images.githubusercontent.com/0000000/placeholder2.png) |

> *(Replace placeholders above with actual Swagger or Postman screenshots.)*

---

## ⚙️ Setup & Run

### 🪴 1. Clone the Repository
```bash
git clone https://github.com/BHARATHI408/linkify-url-shortener.git
cd linkify-url-shortener

🗄️ 2. Configure MySQL
CREATE DATABASE linkify;


Then create this file:
📄 src/main/resources/application.yml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/linkify?useSSL=false&serverTimezone=UTC
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
server:
  port: 8080

🚀 3. Run the App
mvn spring-boot:run


Open Swagger UI → http://localhost:8080/swagger-ui/index.html

🧪 Example APIs
➕ Create Short URL

POST /api/v1/url

{
  "originalUrl": "https://www.google.com",
  "expireDays": 3
}


Response

{
  "shortCode": "pHLwpSI1",
  "originalUrl": "https://www.google.com"
}

🔁 Redirect Short URL

GET /api/v1/url/{shortCode}
➡️ Returns 302 FOUND and redirects to the original URL.

⚠️ Error Handling

Expired link → 410 Gone

Invalid link → 404 Not Found

👨‍💻 Author

Bharath M H


⚖️ License

This project is licensed under the MIT License
.

💙 Made with Spring Boot and Java by Bharath M H