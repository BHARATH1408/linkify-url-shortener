<div align="center">
  <h1>🔗 Linkify – URL Shortener API</h1>
  <p>
    <em>A clean and modern Spring Boot + MySQL backend that shortens URLs and manages link expiry — complete with Swagger UI and JUnit tests.</em>
  </p>

  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.0-green?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-Database-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge"/>
  <br/><br/>
</div>

---

## 🚀 Features
✅ Shorten long URLs  
✅ Optional expiry date for short links  
✅ Redirects using HTTP 302  
✅ Handles expired (410) & invalid (404) links  
✅ Swagger UI for API testing  
✅ Comprehensive JUnit + MockMvc test coverage

---

## 🧰 Tech Stack

| Layer | Technology |
|:------|:------------|
| Backend | Java 17, Spring Boot 3 |
| Database | MySQL 8 / JPA / Hibernate |
| Build Tool | Maven |
| Documentation | Springdoc OpenAPI (Swagger UI) |
| Testing | JUnit 5, MockMvc |

---

## 🖥️ Screenshots (Swagger UI)

> Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

| Create Short URL | Redirect URL |
|:--:|:--:|
| <img src="https://user-images.githubusercontent.com/0000000/placeholder1.png" width="450"/> | <img src="https://user-images.githubusercontent.com/0000000/placeholder2.png" width="450"/> |

*(Optional: Replace placeholders above with your actual screenshots from Swagger or Postman.)*

---

## ⚙️ Setup & Run

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/BHARATHI408/linkify-url-shortener.git
cd linkify-url-shortener

2️⃣ Configure MySQL

Create a new database:

CREATE DATABASE linkify;


Then add the following file:
📁 src/main/resources/application.yml

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

3️⃣ Run the App
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
➡️ Redirects to the original URL (302 Found)

⚠️ Error Handling

Expired link → 410 Gone

Invalid link → 404 Not Found

🧠 Author

Bharath M H
💼 LinkedIn Profile

⚖️ License

This project is licensed under the MIT License
.

<div align="center"> <sub>Made with ❤️ using Spring Boot and Java by <strong>Bharath M H</strong></sub> </div> ```