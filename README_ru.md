# ScheduleApp Server

## Языки
- [English](README.md)
- [Русский](README_ru.md)

### Описание

Этот сервер поддерживает мобильное приложение ScheduleApp, предоставляющее расписание для студентов и преподавателей университета. Сервер разработан на базе Ktor с использованием различных современных библиотек и технологий.

## Технологический стек

- **Языки программирования:** Kotlin, Java
- **Фреймворки и библиотеки:**
  - Ktor (Jwt, Exposed, HikariCP, Serialization, Swagger, Web Sockets)
  - Coroutines + Flow (для работы с асинхронными потоками данных)
  - Koin (для внедрения зависимостей)
  - PostgreSQL
  - JUnit + Mockito (тестирование)
- **Инструменты планирования:**
  - GitHub для управления проектом и версионирования

## Установка и запуск

1. Склонируйте репозиторий:
```bash
    git clone https://github.com/MobileBSU/FAMCS_Schedule_Server.git
```
2. Настройте конфигурационные файлы (например, application.conf) с данными о подключении к базе данных.
3. Соберите и запустите сервер:
```bash
    ./gradlew run
```

## API: Описание эндпоинтов

API сервера сгруппирован по ролям пользователей: **Студенты**, **Преподаватели**, **Администраторы**.

### Общие эндпоинты (для всех пользователей)

#### Авторизация и регистрация
- **POST** /api/auth/login
  - Описание: Вход в систему.
  - Тело запроса:
```json
    {
      "email": "user@example.com",
      "password": "password123"
    }
```
  - Ответ:
```json
    {
      "token": "jwt-token"
    }
```

- **POST** /api/auth/register
  - Описание: Регистрация нового пользователя.
  - Тело запроса:
```json
    {
      "email": "user@example.com",
      "password": "password123",
      "role": "student"
    }
```
  - Ответ: Статус 201 при успешной регистрации.

### Студенты

#### Получение расписания
- **GET** /api/schedule/student
  - Описание: Получение расписания для текущего пользователя.
  - Заголовки:
    - Authorization: Bearer jwt-token
  - Ответ:
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

#### Запросы на изменения расписания
- **POST** /api/schedule/request
  - Описание: Отправить запрос на изменение расписания.
  - Тело запроса:
```json
    {
      "day": "Monday",
      "subject": "Mathematics",
      "reason": "Conflict with another subject"
    }
```
  - Ответ: Статус 200 при успешной отправке.

### Преподаватели

#### Управление расписанием
- **POST** /api/schedule/teacher
  - Описание: Добавление или изменение расписания для преподавателя.
  - Заголовки:
    - Authorization: Bearer jwt-token
  - Тело запроса:
 ```json
    {
      "day": "Monday",
      "subject": "Advanced Physics",
      "time": "14:00-15:30",
      "room": "201"
    }
```
  - Ответ: Статус 200 при успешном обновлении.

#### Просмотр запросов на изменения
- **GET** /api/schedule/requests
  - Описание: Получение списка запросов на изменение расписания от студентов.
  - Ответ:
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

### Администраторы

#### Управление пользователями
- **GET** /api/admin/users
  - Описание: Получение списка пользователей.
  - Заголовки:
    - Authorization: Bearer jwt-token
  - Ответ:
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

- **DELETE** /api/admin/users/{id}
  - Описание: Удаление пользователя по ID.
  - Заголовки:
    - Authorization: Bearer jwt-token
  - Ответ: Статус 200 при успешном удалении.

#### Управление расписанием
- **POST** /api/admin/schedule
  - Описание: Добавление глобальных изменений расписания.
  - Тело запроса:
```json
    {
      "day": "Tuesday",
      "subject": "Chemistry",
      "time": "11:00-12:30",
      "room": "301"
    }
```
  - Ответ: Статус 200 при успешном добавлении.

## Документация API

Полная документация доступна через Swagger по адресу: /swagger

---

Для любых вопросов или предложений создавайте тикеты в репозитории [GitHub](https://github.com/MobileBSU/FAMCS_Schedule_Server.git).
