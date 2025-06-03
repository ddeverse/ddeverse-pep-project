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

## 🧱 Project Architecture

The full documentation of the system architecture—including the controller, service, and DAO layers, request/response flow, and data model—is available in the [Project Wiki](https://github.com/ddeverse/ddeverse-pep-project/wiki/Project-Architecture).

---

## Setup / Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- IDE

### Installation and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ddeverse/ddeverse-pep-project.git
   cd ddeverse-pep-project
   ```
   
2. **Build the project:**
   ```bash
   mvn clean install
   ```
   
3. **Run the application:**
   Launch the `Main.java` file. The server will start on `http://localhost:8080`.
   
4. **Database setup:**
   The `ConnectionUtil` class automatically runs the `schema.sql` script located in `src/main/resources` to initialize the schema.
   
---

## API Usage

### Endpoints

Register a new user:
```bash
POST /register
```

User login
```bash
POST /login
```

Create a new message
```bash
POST /messages
```

Get all messages
```bash
GET /messages
```

Get a message by ID
```bash
GET /messages/{message_id}
```

Update message text
```bash
PATCH /messages/{message_id}
```

Delete a message by ID
```bash
DELETE /messages/{message_id}
```

Get all messages by a specific user
```bash
GET /accounts/{account_id}/messages
```

---

MIT License

Copyright (c) 2025 Dominic Deverse

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
