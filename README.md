<div align="center">
  <h1>🔗 Linkify – URL Shortener API</h1>
  <p>
    <em>A sleek and modern backend service built with Spring Boot and MySQL that shortens URLs, manages link expiry, and provides Swagger-based API documentation.</em>
  </p>

  <p>
    <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?style=for-the-badge"/>
    <img src="https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge"/>
    <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge"/>
  </p>
</div>

---

## 🚀 Features
✅ Shorten long URLs  
✅ Set custom expiration for links  
✅ Redirect with HTTP `302 Found`  
✅ Handle expired (410) & invalid (404) links  
✅ Explore APIs easily via Swagger UI  
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

> 🌐 URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

| 🧩 Create Short URL | 🔁 Redirect URL |
|:--:|:--:|
| <img src="https://user-images.githubusercontent.com/0000000/placeholder1.png" width="450"/> | <img src="https://user-images.githubusercontent.com/0000000/placeholder2.png" width="450"/> |

> *(You can replace the above placeholders with your own Swagger or Postman screenshots.)*

---

## ⚙️ Setup & Run

### 🪴 1. Clone the Repository
```bash
git clone https://github.com/BHARATHI408/linkify-url-shortener.git
cd linkify-url-shortener

🗄️ 2. Configure MySQL

Create a new database:

CREATE DATABASE linkify;


Then add this file:
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


Swagger UI:
👉 http://localhost:8080/swagger-ui/index.html

🧪 API Examples
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

👨## 👨‍💻 Author

<div align="center">
  <b>Bharath M H</b>  
  <br/>
  <a href="https://www.linkedin.com/in/bharath-m-h-69a167223" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-Connect-blue?style=for-the-badge&logo=linkedin" alt="LinkedIn"/>
  </a>
</div>

---

## ⚖️ License
This project is licensed under the [MIT License](./LICENSE).

---

<div align="center">
  <sub>💙 Made with Spring Boot and Java by <b>Bharath M H</b></sub>
</div>
