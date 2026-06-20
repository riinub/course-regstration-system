# Course Registration System

## Overview

The Course Registration System is a console-based Java application that allows students to register and deregister courses while maintaining enrollment records and course information.

The project demonstrates object-oriented programming concepts, file handling, exception handling, and data structure usage through a practical academic management system.

## Features

* Student registration and management
* Course enrollment and deregistration
* Course capacity validation
* Enrollment tracking
* Persistent data storage using files
* Student profile management
* Course catalog management
* Exception handling for registration constraints

## System Architecture

### Student Class

Responsible for:

* Student information management
* Course registration tracking
* Course deregistration
* Student data persistence

### Course Class

Responsible for:

* Course information management
* Enrollment tracking
* Seat limit enforcement
* Course data persistence

### Registrar Class

Responsible for:

* Student enrollment operations
* Registration validation
* Deregistration processing

## Key Concepts Implemented

### Object-Oriented Programming

The application follows an object-oriented design using:

* Encapsulation
* Class abstraction
* Object interaction
* Modular architecture

### Exception Handling

Custom validation ensures that students cannot enroll in courses that have reached maximum capacity.

### File Handling

Student and course data are automatically saved and loaded using text files, allowing information to persist between program executions.

### Data Structures

The system utilizes:

* HashMap for efficient storage and retrieval of student and course records
* ArrayList for managing enrolled students and registered courses

## Technologies Used

* Java
* Object-Oriented Programming
* File I/O
* Exception Handling
* HashMap
* ArrayList

## Learning Outcomes

Through this project I gained practical experience with:

* Java OOP Design
* Data Structures
* File Persistence
* Exception Handling
* System Design
* User Input Validation

## Future Improvements

* Graphical User Interface (GUI)
* Database Integration (MySQL/PostgreSQL)
* Authentication System
* Student Course Search
* Semester Management
* Course Prerequisite Validation
* Faculty Management Module
