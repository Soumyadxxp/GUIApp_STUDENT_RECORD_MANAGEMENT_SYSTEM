# 🎓 Student Record Management System

A desktop-based Student Record Management System developed using **Java Swing** and **Oracle Database 11g XE**. The application provides an intuitive graphical user interface (GUI) for managing student records, storing data securely in an Oracle database, and performing efficient search operations.

---

## 🚀 Features

### Student Management

* Add New Student Records
* Save Student Information to Oracle Database
* Display All Student Records
* Automatic Student ID Generation
* Form Validation

### Search Functionality

* Search Student by ID
* Search Student by Name
* Search Student by Course
* Display Search Results in Table Format

### Database Integration

* Oracle Database 11g XE Connectivity
* Automatic Table Creation
* Persistent Data Storage
* SQL Query Execution

### User Interface

* Java Swing GUI
* Interactive Forms
* JTable Data Display
* Search Dialog Windows
* Responsive Layout

---

## 🛠️ Technology Stack

| Technology             | Purpose                   |
| ---------------------- | ------------------------- |
| Java                   | Core Programming Language |
| Java Swing             | Graphical User Interface  |
| JDBC                   | Database Connectivity     |
| Oracle Database 11g XE | Database Management       |
| SQL                    | Data Manipulation         |

---

## 🗄️ Database Schema

### STUDENT Table

```sql
CREATE TABLE STUDENT
(
    ID NUMBER(4) PRIMARY KEY,
    NAME VARCHAR2(20),
    ADDRESS VARCHAR2(20),
    PHONE VARCHAR2(10),
    SEX VARCHAR2(6),
    COURSE VARCHAR2(5)
);
```

### Table Fields

| Column  | Description     |
| ------- | --------------- |
| ID      | Student ID      |
| NAME    | Student Name    |
| ADDRESS | Student Address |
| PHONE   | Contact Number  |
| SEX     | Gender          |
| COURSE  | Course Name     |

---

## ⚙️ Oracle Database Configuration

### JDBC Driver

```java
Class.forName("oracle.jdbc.driver.OracleDriver");
```

### Connection URL

```java
DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:XE",
    "HR",
    "hr"
);
```

### Default Database Credentials

| Username | Password |
| -------- | -------- |
| HR       | hr       |

---

## 📋 Functional Modules

### Add Student

* Generate Student ID
* Enter Student Details
* Submit Record

### Save Records

* Insert Records into Oracle Database
* Prevent Data Loss
* Store Information Permanently

### Search Student

* By Student ID
* By Student Name
* By Course

### View Records

* Display Complete Student List
* Sort by Student ID

---

## 🎯 Key Concepts Implemented

* Java Swing GUI Programming
* JDBC Connectivity
* Oracle Database Integration
* Event Handling
* JTable Management
* Dialog-Based Searching
* SQL Query Processing
* Object-Oriented Programming (OOP)

---

## ▶️ How to Run

### Step 1: Start Oracle Database

Ensure Oracle Database 11g XE is running.

### Step 2: Create HR User

Use Oracle SQL Developer or SQL*Plus:

```sql
ALTER USER HR IDENTIFIED BY hr;
GRANT CONNECT, RESOURCE TO HR;
```

### Step 3: Add Oracle JDBC Driver

Add:

```text
ojdbc6.jar
```

or

```text
ojdbc8.jar
```

to your project's library path.

### Step 4: Compile and Run

```bash
javac *.java
java MainClass
```

---

## 🖥️ Application Features

✅ Student Registration

✅ Oracle Database Storage

✅ Search by ID

✅ Search by Name

✅ Search by Course

✅ JTable Record Display

✅ Automatic Table Creation

✅ Desktop GUI Application

---

## 📚 Educational Objectives

This project demonstrates practical implementation of:

* Java Swing Application Development
* Oracle Database Programming
* JDBC Connectivity
* Event-Driven Programming
* Desktop Application Design
* Student Information Management Systems

---

## 👨‍💻 Author

**Soumyadeep Basu**

Java Swing + Oracle Database Project

---

## 📜 License

This project is developed for educational and academic purposes.
