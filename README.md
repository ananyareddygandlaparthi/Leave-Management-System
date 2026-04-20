# 🏢 Leave Management System

A full-stack **Spring Boot** web application for managing employee leave requests, featuring a rich frontend dashboard and a backend architected using five classical **OOAD design patterns**.

---

## 📌 Overview

This Leave Management System allows employees to submit and track leave requests, managers to approve or reject them, and admins to generate reports — all through a modern, interactive web UI. The backend is deliberately structured to demonstrate **Object-Oriented Analysis and Design (OOAD)** principles in a real-world application context.

---

## ✨ Features

- **Role-Based Access** — Separate workflows for Employees, Managers, and Admins
- **Leave Request Lifecycle** — Submit, approve, reject, and track leave requests with full status history
- **Team Calendar** — Visual calendar showing team leave schedules (Facade pattern)
- **Real-time Notifications** — Push-style alerts on status changes (Observer pattern)
- **Leave Type Management** — Configurable leave categories (Factory pattern)
- **Access Control** — Permission-based resource gating (Proxy pattern)
- **State Machine** — Leave request status transitions modeled as a formal State pattern
- **In-Memory Database** — H2 with auto-seeded demo data (no external DB required)
- **REST API** — JSON endpoints consumed by a vanilla JS frontend

---

## 🏗️ Design Patterns Implemented

| Pattern | Location | Purpose |
|---|---|---|
| **Factory** | `patterns/factory/` | Creates `LeaveType` objects (Casual, Sick, Earned, etc.) without exposing construction logic |
| **Observer** | `patterns/observer/` | Notifies employees/managers when leave request status changes |
| **State** | `patterns/state/` | Models `LeaveRequest` lifecycle: `PENDING → APPROVED / REJECTED / CANCELLED` |
| **Proxy** | `patterns/proxy/` | Guards service methods based on user roles (Employee/Manager/Admin) |
| **Facade** | `patterns/facade/` | `TeamCalendarFacade` aggregates leave data from multiple services into a single clean DTO for the frontend |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Backend Framework** | Spring Boot 3.2.5 |
| **Language** | Java 17 |
| **ORM / Persistence** | Spring Data JPA + Hibernate |
| **Database** | H2 (in-memory, auto-configured) |
| **Build Tool** | Maven 3.9.6 (bundled wrapper) |
| **Frontend** | Vanilla HTML + CSS + JavaScript |
| **API Style** | RESTful JSON |

---

## 🚀 Setup & Running

### Prerequisites

- **Java 17** or higher ([Download](https://adoptium.net/))
- No other installations required — Maven and H2 are bundled

### 1. Clone the repository

```bash
git clone https://github.com/ananyareddygandlaparthi/Leave-Management-System.git
cd Leave-Management-System
```

### 2. Build the project

```bash
# Windows
.\apache-maven-3.9.6\bin\mvn.cmd clean install

# macOS / Linux
./apache-maven-3.9.6/bin/mvn clean install
```

### 3. Run the application

```bash
# Windows
.\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run

# macOS / Linux
./apache-maven-3.9.6/bin/mvn spring-boot:run
```

### 4. Open in browser

```
http://localhost:8080
```

> The H2 database is automatically seeded with demo users, managers, and leave data on startup via `DatabaseInitializer.java`.

---

## 📂 Project Structure

```
Leave_Management_System-main/
├── pom.xml                          # Maven build descriptor
├── apache-maven-3.9.6/             # Bundled Maven (no system install needed)
└── src/
    └── main/
        ├── java/com/ooad/leave/
        │   ├── LeaveManagementApplication.java   # Spring Boot entry point
        │   ├── config/
        │   │   └── DatabaseInitializer.java      # Demo data seeder
        │   ├── controller/
        │   │   └── MainController.java           # REST API endpoints
        │   ├── model/                            # JPA entities
        │   │   ├── User.java
        │   │   ├── Employee.java
        │   │   ├── Manager.java
        │   │   ├── Admin.java
        │   │   ├── LeaveRequest.java
        │   │   ├── LeaveType.java
        │   │   ├── Notification.java
        │   │   └── Report.java
        │   ├── repository/                       # Spring Data JPA repos
        │   ├── service/                          # Business logic layer
        │   └── patterns/                         # OOAD design patterns
        │       ├── facade/
        │       │   ├── TeamCalendarFacade.java
        │       │   └── CalendarEventDTO.java
        │       ├── factory/
        │       ├── observer/
        │       ├── proxy/
        │       └── state/
        └── resources/
            ├── application.properties            # Spring config
            └── public/                           # Static frontend
                ├── index.html
                ├── css/
                └── js/
                    └── app.js                    # Frontend logic
```

---

## 🔌 Key API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/employees` | List all employees |
| `GET` | `/api/leave-requests` | Get all leave requests |
| `POST` | `/api/leave-requests` | Submit a new leave request |
| `PUT` | `/api/leave-requests/{id}/approve` | Approve a request (Manager) |
| `PUT` | `/api/leave-requests/{id}/reject` | Reject a request (Manager) |
| `GET` | `/api/team-calendar` | Get team calendar events (Facade) |
| `GET` | `/api/notifications` | Get notifications for a user |
| `GET` | `/api/reports` | Generate leave summary report (Admin) |

---

## 🗄️ H2 Console (Development)

Access the in-memory database browser at:

```
http://localhost:8080/h2-console
```

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:leavedb` |
| Username | `sa` |
| Password | *(leave blank)* |

---

