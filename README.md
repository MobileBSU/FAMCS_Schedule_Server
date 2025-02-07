# ScheduleApp Server

## Languages
- [English](README.md)
- [Русский](README_ru.md)

### Description

This server supports the ScheduleApp mobile application, providing schedules for university students and teachers. The server is built with Ktor, using various modern libraries and technologies.

## Tech Stack

- **Programming Languages:** Kotlin, Java
- **Frameworks and Libraries:**
  - Ktor (Jwt, Exposed, HikariCP, Serialization, Swagger, Web Sockets)
  - Coroutines + Flow (for handling asynchronous data streams)
  - Koin (for dependency injection)
  - PostgreSQL
  - JUnit + Mockito (testing)
- **Planning Tools:**
  - GitHub for project management and version control

## Installation and Running

1. Clone the repository:
    ```bash
    git clone https://github.com/MobileBSU/FAMCS_Schedule_Server.git
    ```
2. Configure the necessary files (e.g., `application.conf`) with database connection details.
3. Build and run the server:
    ```bash
    ./gradlew run
    ```

## API: Endpoint Overview

The server's API is grouped by user roles: **Students**, **Teachers**, **Administrators**.

### General Endpoints (for all users)

#### Authentication and Registration
- **POST** `/api/auth/login`
  - Description: Logs in a user.
  - Request Body:
    ```json
    {
      "email": "user@example.com",
      "password": "password123"
    }
    ```
  - Response:
    ```json
    {
      "token": "jwt-token"
    }
    ```

- **POST** `/api/auth/register`
  - Description: Registers a new user.
  - Request Body:
    ```json
    {
      "email": "user@example.com",
      "password": "password123",
      "role": "student"
    }
    ```
  - Response: Status 201 on successful registration.

### Students

#### Retrieve Schedule
- **GET** `/api/schedule/student`
  - Description: Retrieves the schedule for the current user.
  - Headers:
    - Authorization: `Bearer jwt-token`
  - Response:
    ```json
    [
      {
        "day": "Monday",
        "subject": "Mathematics",
        "time": "10:00-11:30",
        "room": "101"
      },
      {
        "day": "Monday",
        "subject": "Physics",
        "time": "12:00-13:30",
        "room": "102"
      }
    ]
    ```

#### Request Schedule Changes
- **POST** `/api/schedule/request`
  - Description: Submits a request to modify the schedule.
  - Request Body:
    ```json
    {
      "day": "Monday",
      "subject": "Mathematics",
      "reason": "Conflict with another subject"
    }
    ```
  - Response: Status 200 on successful submission.

### Teachers

#### Manage Schedule
- **POST** `/api/schedule/teacher`
  - Description: Adds or updates a schedule entry for a teacher.
  - Headers:
    - Authorization: `Bearer jwt-token`
  - Request Body:
    ```json
    {
      "day": "Monday",
      "subject": "Advanced Physics",
      "time": "14:00-15:30",
      "room": "201"
    }
    ```
  - Response: Status 200 on successful update.

#### View Change Requests
- **GET** `/api/schedule/requests`
  - Description: Retrieves a list of schedule change requests from students.
  - Response:
    ```json
    [
      {
        "id": 1,
        "day": "Monday",
        "subject": "Mathematics",
        "reason": "Conflict with another subject"
      }
    ]
    ```

### Administrators

#### User Management
- **GET** `/api/admin/users`
  - Description: Retrieves a list of users.
  - Headers:
    - Authorization: `Bearer jwt-token`
  - Response:
    ```json
    [
      {
        "id": 1,
        "email": "student1@example.com",
        "role": "student"
      },
      {
        "id": 2,
        "email": "teacher1@example.com",
        "role": "teacher"
      }
    ]
    ```

- **DELETE** `/api/admin/users/{id}`
  - Description: Deletes a user by ID.
  - Headers:
    - Authorization: `Bearer jwt-token`
  - Response: Status 200 on successful deletion.

#### Schedule Management
- **POST** `/api/admin/schedule`
  - Description: Adds global schedule changes.
  - Request Body:
    ```json
    {
      "day": "Tuesday",
      "subject": "Chemistry",
      "time": "11:00-12:30",
      "room": "301"
    }
    ```
  - Response: Status 200 on successful addition.

## API Documentation

Full documentation is available via Swagger at: `/swagger`

---

For any questions or suggestions, feel free to create issues in the [GitHub repository](https://github.com/MobileBSU/FAMCS_Schedule_Server.git).
