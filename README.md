# Employee Management System

This project is an application for managing a company's employee list using a RESTful API and a database. The system supports different types of employees: workers, managers, and others (such as executives, secretaries, etc.). The application allows adding, deleting, changing employee types, and linking employees to managers. It also supports sorting the list by last names and hire dates.

## Functionality

### Employee Types:
1. **Employee**:
    - Full Name (First Name, Last Name)
    - Date of Birth
    - Hire Date

2. **Manager**:
    - Full Name (First Name, Last Name)
    - Date of Birth
    - Hire Date
    - List of subordinates (employees)

3. **Others (Executives, Secretaries, etc.)**:
    - Full Name (First Name, Last Name)
    - Date of Birth
    - Hire Date
    - Text description of the employee

### Features:
- Add new employees.
- Delete employees.
- Change the type of employee.
- Assign employees to a manager.
- Sort the list of employees by last name or hire date.

### RESTful API:
The system provides an API for interacting with the database, including all necessary CRUD operations for managing employees.

## Technologies

- **Spring Boot** — for building the RESTful API.
- **Hibernate** — for ORM-based interaction with the database.
- **Liquibase** — for database migrations.
- **PostgreSQL**  — for storing employee data.
- **JUnit** — for testing the API.

## Database Structure

![image](https://github.com/user-attachments/assets/b66f47e9-e01e-4441-9c1e-ab46898622ec)

