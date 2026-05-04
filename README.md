# 📊 System Metrics Dashboard

A real-time monitoring dashboard built with **Spring Boot + Angular + PostgreSQL + Chart.js**.

## ✨ Features

* View system metrics (CPU, memory, active connections)
* Line chart visualization (CPU usage over time)
* REST API integration
* Clean Angular UI

## 🧱 Tech Stack

* **Backend:** Spring Boot, JdbcTemplate
* **Frontend:** Angular (standalone), HttpClient
* **DB:** PostgreSQL (Docker)
* **Charts:** Chart.js

## 📂 Project Structure

```
backend/   -> Spring Boot API
frontend/  -> Angular app
```

## ▶️ Run Locally

### 1. Database (Docker)

```bash
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres -v pgdata:/var/lib/postgresql/data postgres
```

### 2. Backend

```bash
cd backend
mvn spring-boot:run
```

### 3. Frontend

```bash
cd frontend
npm install
ng serve
```

Open: http://localhost:4200

## 🔌 API

* `GET /api/transactions` → returns metrics mapped for UI

## 📸 Preview

*Add a screenshot here (see step below).*

## 🚀 Roadmap

* [ ] Memory & connections charts
* [ ] Auto-refresh / live updates
* [ ] Alerts (CPU > threshold)
* [ ] Auth + roles

## 👤 Author

Md Uzaifa
