## Train Ticketing System

This project is a Java Spring Boot web application built for the Siemens Internship assignment. It simulates a complete train ticketing system with both user and administrator functionalities.

## Technologies used

Java

Spring Boot 3.2.5 (Web, Data JPA, Security, Validation)

PostgreSQL database

Thymeleaf and Bootstrap for the frontend

Gradle for dependency management

## Main features

Smart route searching: Users can search for direct train connections or connections with one transfer between stations.

Booking system: Users can book tickets, and the system automatically updates the number of available seats. Concurrency is handled using optimistic locking to prevent overbooking.

Admin control panel: Administrators can add new trains, create stations, define complex routes, and schedule departures.

Delay management: Admins can mark trains as delayed, which triggers a simulated email notification to all affected passengers.

Secure access: Passwords are encrypted using SHA-256 hashing.

## How to run the project locally

Make sure you have Java and PostgreSQL installed on your computer.

Open pgAdmin or your database tool and create a new database called siemens.

The application uses environment variables for database credentials for security reasons. You can set DB_USERNAME and DB_PASSWORD on your machine, or the system will safely default to local credentials.

Open the project in your IDE and run the TrainApplication main class.

The app will start on port 8080. Open your browser and go to http://localhost:8080.

## Default test accounts

The database is automatically populated with some test data on startup so you can easily test the features. You can log in using:

Admin account: admin@train.com (password: password)

User account: user@train.com (password: password)