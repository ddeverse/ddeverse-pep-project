# Social Media Blog API

## Overview

**Social Media Blog API** is a backend RESTful API for a micro-blogging social media platform. It supports user account registration, login authentication, message posting, message editing, deletion, and retrieval (both globally and user-specific). This API provides all essential functionality to support a lightweight frontend client for a blogging or messaging app.

The project was designed following a **three-layer architecture** pattern (Controller → Service → DAO), using a relational database and JDBC for persistence.

---

## Features Implemented

- User registration with validation
- User login authentication
- Create and post messages
- Retrieve all messages
- Retrieve individual message by ID
- Update message text
- Delete message by ID
- Retrieve all messages posted by a specific user

---

## Technologies Used

- Java 11
- JDBC (Java Database Connectivity)
- H2 / PostgreSQL (configurable)
- Maven
- JUnit 5
- SQL DDL Scripts

---

## Setup / Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- IDE

### Installation and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/social-media-blog-api.git
   cd social-media-blog-api
   ```
   
2. **Build the project:**
   ```bash
   mvn clean install
   ```
   
3. **Run the application:**
   Launch the <mark>Main.java</mark> file. The server will start on <mark>http://localhost:8080</mark>.
   
4. **Database setup:**
   The <mark>ConnectionUtil</mark> class automatically runs the <mark>schema.sql</mark> script located in <mark>src/main/resources</mark> to initialize the schema.

   
