# Student Management System

A simple console-based **Student Management System** written in Java that demonstrates basic CRUD (Create, Read, Update, Delete) operations using **PostgreSQL** as the backend database.

---

## Table of Contents

* [About](#about)
* [Features](#features)
* [Tech Stack](#tech-stack)
* [Project Structure](#project-structure)
* [Database Setup](#database-setup)
* [Configuration](#configuration)
* [How to Build & Run](#how-to-build--run)
* [Usage](#usage)
* [Extending the Project](#extending-the-project)
* [Troubleshooting](#troubleshooting)
* [Contributing](#contributing)
* [License](#license)

---

## About

This is a minimal, educational Java application that interacts with a PostgreSQL database to manage student records. It uses plain JDBC (no ORM) and a console-based menu for user interaction.

---

## Features

* Add a student
* Update student details
* Fetch (read) a student by ID
* Delete a student by ID
* Console-based interactive menu

---

## Tech Stack

* Java (JDK 8+)
* PostgreSQL
* JDBC (PostgreSQL JDBC driver)
* Build/run via command line or any Java IDE (Eclipse, IntelliJ, NetBeans)

---

## Project Structure

This project follows a simple package layout (example based on code provided):

```
src/
└── school/
    ├── driver/
    │   ├── StudentDriver.java      // main program (console menu)
    │   └── StudentService.java     // database operations using JDBC
    ├── entity/
    │   └── Student.java            // Student model (id, name, age)
    └── (other packages as needed)
```

**Important classes**

* `StudentDriver` — Console-based UI that accepts user input and calls service methods.
* `StudentService` — Handles database connection (JDBC) and CRUD queries.
* `Student` — Entity/model class with fields `id`, `name`, and `age`.

---

## Database Setup

1. Install PostgreSQL and make sure the server is running.
2. Create a database (example: `school`).

```sql
-- Connect as postgres user and run:
CREATE DATABASE school;
\c school;

-- Create the `student` table:
CREATE TABLE student (
  id INTEGER PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INTEGER
);
```

3. Adjust the connection URL / credentials in `StudentService` if you use different username/password, host, port, or database name.

The code uses this JDBC URL by default (from `StudentService`):

```
jdbc:postgresql://localhost:5432/school?user=postgres&password=123
```

If you prefer to supply credentials separately (recommended for production), modify `StudentService` to construct the URL and pass user/password to `DriverManager.getConnection(url, user, password)` instead of embedding credentials in the URL.

---

## Configuration

### Required libraries

* PostgreSQL JDBC driver (e.g., `postgresql-<version>.jar`).

If you are using Maven, add this dependency to `pom.xml`:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.6.0</version>
</dependency>
```

(Use the JDBC driver version compatible with your environment.)

---

## How to Build & Run

### Option A — Using an IDE

1. Import the project into your favorite IDE (IntelliJ/Eclipse/NetBeans).
2. Add the PostgreSQL JDBC driver to the project classpath (via project settings or Maven/Gradle).
3. Ensure PostgreSQL is running and `student` table exists.
4. Run `school.driver.StudentDriver` as a Java application.

### Option B — Using Command Line (javac/java)

1. Place the PostgreSQL JDBC driver `postgresql-<version>.jar` in a folder, e.g. `lib/`.
2. Compile sources (from project root):

```bash
javac -cp "lib/*" -d out src/school/entity/Student.java src/school/driver/StudentService.java src/school/driver/StudentDriver.java
```

3. Run the application:

```bash
java -cp "out:lib/*" school.driver.StudentDriver
```

> On Windows, replace `:` with `;` in the classpath.

---

## Usage

When you run the program you'll see the console menu:

```
Welcome to the our School
press 1 to Add Student
press 2 to Update Student
press 3 to Fetch Student
press 4 to Delete Student
press 5 to Exit
```

Follow on-screen prompts to provide ID, name, and age for operations.

**Notes:**

* `fetch(id)` returns a `Student` object if found, otherwise prints "No student found with id: <id>".
* `save`, `update`, and `delete` return an integer (number of affected rows) — non-zero means success.

---

## Extending the Project (Ideas)

* Add input validations (e.g., prevent duplicate IDs, validate age ranges).
* Add more fields to `Student` (email, address, class/grade).
* Implement list-all students feature.
* Use a configuration file or environment variables for DB credentials.
* Replace JDBC with an ORM (Hibernate / JPA) for more features.
* Add unit tests for `StudentService` (mock the DB connection or use an in-memory DB for tests).
* Add a simple GUI (Swing/JavaFX) or build a REST API on top of the service.

---

## Troubleshooting

* **`ClassNotFoundException: org.postgresql.Driver`** — make sure the PostgreSQL JDBC jar is on the classpath.
* **Connection errors** — double-check the JDBC URL, DB credentials, that PostgreSQL is running, and that the `student` table exists.
* **Permission errors** — ensure the DB user has privileges to create/read/update/delete rows.

---

## Contributing

Contributions are welcome. If you'd like to contribute:

1. Fork the repo
2. Create a feature branch
3. Submit a Pull Request with a clear description of changes

---

## License

This project is provided as-is for educational purposes. Add a license if you want to open-source this project (e.g., MIT License).

---

## Contact

If you need help setting up or want features added, open an issue in the repository or contact the author.
