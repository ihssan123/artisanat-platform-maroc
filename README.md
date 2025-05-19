# 🧵 Bazary – Moroccan Handicraft Management Platform

**Bazary** is a web platform designed to **digitize the presence of Moroccan artisans** by providing them with a personalized space to showcase and sell their handmade products. It allows clients to browse authentic crafts and enables administrators to manage users and content efficiently.

---

## 📌 Project Background

Moroccan handicrafts are a key part of the country's cultural and economic identity. However, many artisans lack access to digital tools to present their craftsmanship online. **Bazary** aims to bridge that gap by connecting **local artisans** with **clients**, and by promoting traditional products through a modern and user-friendly interface.

---

## 🎯 Project Objectives

- Digitize the online presence of Moroccan artisans.
- Provide clients with easy access to authentic artisanal products.
- Offer artisans a dashboard to manage orders, products, and profiles.
- Build an admin interface to manage users and platform content.

---

## 🧩 Functional Scope

### 👤 Artisan
- Register and log in
- Create and manage artisan profile
- Add / edit / delete products
- View received orders

### 🛍️ Client
- Browse freely or with an account
- Search by category, product, or location
- View artisan profiles
- Place orders or request custom quotes

### 🛠️ Admin
- Manage user accounts
- Approve or remove profiles and products
- Access statistical dashboards

---

## 👥 Target Users

- **Artisans**: sellers of Moroccan crafts (pottery, leather, textiles, etc.)
- **Clients**: individuals or professionals interested in handmade products
- **Administrators**: internal managers of the platform

---

## ⚙️ Technical Stack

| Layer        | Technology                     |
|--------------|--------------------------------|
| Backend      | Java Spring Boot + JWT         |
| Frontend     | Angular (modular architecture) |
| Database     | MongoDB *(or MySQL optionally)*|
| Hosting      | Docker / Cloud provider        |
| Authentication | JWT                         |
| Responsive   | ✅ Web & Mobile friendly        |
| Language     | 🇫🇷 French *(Arabic & English planned)* |

---

## 🚀 Getting Started

### 📦 Prerequisites

- Node.js & Angular CLI
- Java 17+
- Maven
- Docker
- MongoDB or MySQL

### ⚙️ Backend

```bash
cd backend/
mvn clean install
# Run via Spring Boot or Docker
cd frontend/
npm install
ng serve
