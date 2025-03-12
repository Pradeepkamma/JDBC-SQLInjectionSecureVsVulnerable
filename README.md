
# JDBC SQL Injection Demo & Secure Login

This project demonstrates **SQL Injection vulnerability** in JDBC and how to prevent it using **PreparedStatement**.

## 🚀 Overview
- `SQLInjectionDemo.java` → **Vulnerable** to SQL Injection (Uses `Statement`)
- `SecureLoginDemo.java` → **Secured** against SQL Injection (Uses `PreparedStatement`)

## 🛠 Technologies Used
- **Java**
- **JDBC (Java Database Connectivity)**
- **MySQL**

## 📌 Database Setup
1. **Create a MySQL database**:  
   ```sql
   CREATE DATABASE jdbcdemo;
Use the database:

USE jdbcdemo;

Create a users table:

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
Insert sample users:

INSERT INTO users (username, password) VALUES ('admin', 'admin123');

INSERT INTO users (username, password) VALUES ('user', 'user123');

## ⚠️ SQL Injection Vulnerability

File: SQLInjectionDemo.java

String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + userPassword + "'";

## ✅ If a user enters:

Username: admin' -- 

Password: anything

🚨 Hacker logs in without a password! 🚨

The query becomes:

SELECT * FROM users WHERE username = 'admin' --' AND password = 'anything'

👉 Everything after -- is ignored, logging the attacker in without the correct password.

## ✅ Preventing SQL Injection

File: SecureLoginDemo.java

String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

PreparedStatement pstmt = conn.prepareStatement(sql);

pstmt.setString(1, username);

pstmt.setString(2, userPassword);

✅ Uses PreparedStatement to prevent SQL Injection.

Even if a hacker enters ' OR '1'='1 --, the query remains safe.

## 🔐 Why is PreparedStatement Secure?

Prevents SQL Injection

Separates SQL logic from user input

Ensures input is treated as data, not code

Recommended Best Practice for JDBC

🚀 Always use PreparedStatement in real-world applications!
