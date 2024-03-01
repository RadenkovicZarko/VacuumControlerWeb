# User Management and Vacuum Control Web Application
This project aims to develop a web application for managing users and their permissions, as well as controlling vacuum robots within a simulated environment. The application provides support for CRUD operations on users, enforcing request authorization through a defined set of permissions. Additionally, it introduces functionalities to manage vacuum robots, including scheduling operations and tracking errors.

## Features
User Management: Perform CRUD operations on users with authorization based on permissions (can_create_users, can_read_users, can_update_users, can_delete_users).
Authentication: Implement user authentication using JWTs, requiring email and password for login.
Vacuum Control: Manage vacuum robots with actions like SEARCH, START, STOP, DISCHARGE, ADD, and REMOVE, each guarded by corresponding permissions.
Scheduling Operations: Schedule START, STOP, and DISCHARGE operations for vacuums and track execution errors in a dedicated log.


## Technologies
Backend: Spring or JBoss
Database: Relational Database Management System
Frontend: Angular 2+



## Prerequisites
JDK 1.8 or later
Maven 3.2+
Node.js and npm
Angular CLI
A relational database (MySQL, PostgreSQL, etc.)

## Setting Up the Backend

Clone the repository:
Navigate to the backend directory:
Configure the database connection in application.properties or application.yml.
Run the backend application

## Setting Up the Frontend


Navigate to the frontend directory:
Install dependencies: npm install
Start the Angular application: ng serve
Visit http://localhost:4200/ in your web browser.


## User Authentication and Permissions
The application supports logging in with different user accounts, each having specific permissions that dictate access to various functionalities within the application. User credentials and their associated permissions are pre-defined in the Hibernate database and are initialized using the import.sql file located in the backend setup.

## Initializing User Credentials with import.sql
The import.sql file contains SQL statements that are automatically executed by Hibernate upon application startup. This file is used to populate the database with initial data, including user accounts and their permissions. Here's how it works:
Location: The file is located at backendNVP3/src/main/resources/import.sql.
Contents: It should contain INSERT statements that define each user's email, hashed password, and permissions. 

## Logging In
To log in:

Open the Login Page: Access the application through your web browser at http://localhost:4200/login.
Enter Credentials: Use the email and password for one of the users defined in the import.sql file.
Explore Based on Permissions: Once logged in, the application's UI will adapt based on the logged-in user's permissions, enabling or disabling access to specific functionalities.
Different Users, Different Possibilities
Each user in the system has a set of permissions that define what actions they can perform:

Users with can_read_users permission can view the list of all users.
Users with can_create_users permission can access the form to add new users.
Users with can_update_users permission can edit user details.
Users with can_delete_users permission can remove users from the system.
Users without the necessary permissions for a specific action will receive a 403 Forbidden status code if they attempt to perform unauthorized operations.


## Usage
Login: Use the login page to authenticate.
Manage Users: Navigate to the user management page to add, view, update, or delete users based on your permissions.
Control Vacuums: Access the vacuum control page to manage your vacuum robots, schedule operations, and view operation errors.
