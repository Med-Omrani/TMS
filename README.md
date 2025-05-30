# 🚚 Transportation Management System (TMS)

A full-stack application to manage drivers, trailers, and vehicles using:

- **Frontend**: Angular  
- **Backend**: Spring Boot (Java)  
- **Database**: MongoDB

---

## ✅ Features

- Manage **Drivers**, **Trailers**, and **Vehicles**
- Assign drivers to vehicles
- CRUD operations on all entities
- RESTful API with Spring Boot
- Responsive UI with Angular

---

## 🔧 Technologies Used

| Layer      | Technology         |
|-----------|--------------------|
| Frontend  | Angular, TypeScript, HTML/CSS |
| Backend   | Java, Spring Boot, MongoDB |
| Database  | MongoDB            |

---

## 📁 Backend Structure
back-end/
├── controller/ → REST APIs
├── entity/ → MongoDB documents (Driver, Trailer, Vehicle)
├── repository/ → MongoDB repositories
├── service/ → Business logic
├── TmsApplication.java → Main class
└── application.properties → Config (MongoDB connection, etc.)

---

## 🖥️ Frontend Structure
front-end/
├── pages/ → Feature-specific components
├── components/shared/ → Reusable components (footer, header)
├── services/ → API interaction services
├── app-routing.module → Navigation setup
└── assets/ → Images and static files

---

## 🛠️ Setup Instructions

### Backend (Spring Boot + MongoDB)

1. Install MongoDB and make sure it's running.
2. Update MongoDB connection in `application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/tms_db

Run the backend:
mvn spring-boot:run

Frontend (Angular)
1. Make sure Node.js and Angular CLI are installed.
Install dependencies:
npm install
ng serve
