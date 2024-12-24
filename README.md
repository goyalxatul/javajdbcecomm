# E-Commerce Management System
### [Project Report](https://drive.google.com/file/d/13CnpkpngUnZlMo5Npx7hNN6SvX1tHFI5/view?usp=drive_link)

Welcome to the E-Commerce Management System repository! This project simplifies the management of an e-commerce platform, providing a robust solution for handling various aspects of product, order, and customer administration.

The software is developed using Java and the NetBeans IDE and integrates seamlessly with a MySQL database using JDBC. 

## Table of Contents

- [Introduction](#introduction)
- [Problem Statement](#problem-statement)
- [Modules](#modules)
- [Entities and Attributes](#entities-and-attributes)
- [Relationships](#relationships)
- [Relational Schema](#relational-schema)
- [ER Diagram](#er-diagram)
- [Use Case Diagram](#use-case-diagram)
- [System Architecture](#system-architecture)
- [Technology Stack](#technology-stack)
- [Frontend Sample](#frontend-sample)
- [License](#license)
- [Author](#author)

## Introduction

In today’s competitive market, it is crucial to have a comprehensive and efficient e-commerce management system. This project introduces a solution for managing products, orders, customers, and financial transactions within an online shopping platform. Key functionalities include inventory management, user authentication, order tracking, and payment handling.

The system is built using Java and the NetBeans IDE, ensuring a robust development process. It integrates with a MySQL database via JDBC for efficient data management. 
## Problem Statement

E-commerce businesses face challenges in managing complex operations, such as tracking inventory, handling customer data, and managing orders efficiently. Manual and fragmented approaches lead to inefficiencies and errors. This project provides an integrated platform to address these challenges, streamlining operations and enhancing customer experience.

## Modules

The system includes the following modules:
- **Login Module**: User authentication and role-based access.
- **Admin Module**: Management of products, categories, orders, and users.
- **Customer Module**: User-friendly interface for product browsing, cart management, and order placement.
- **Product Module**: Inventory and product catalog management.
- **Order Module**: Order processing and tracking.
- **Payment Module**: Secure payment processing.
- **Report Module**: Analytics and sales reporting.

## Entities and Attributes

1. **Admin**
    - Attributes: `AdminID`, `Name`, `Email`, `Password`

2. **Customer**
    - Attributes: `CustomerID`, `Name`, `Email`, `Phone Number`, `Address`

3. **Product**
    - Attributes: `ProductID`, `Name`, `Category`, `Price`, `Stock`

4. **Order**
    - Attributes: `OrderID`, `CustomerID`, `Date`, `Total Amount`, `Status`

5. **Order Details**
    - Attributes: `OrderID`, `ProductID`, `Quantity`, `Price`

6. **Payment**
    - Attributes: `PaymentID`, `OrderID`, `CustomerID`, `Payment Method`, `Payment Date`, `Amount`


## Relational Schema

1. **Admin**
    - **Attributes**: `AdminID`, `Name`, `Email`, `Password`
    - **Primary Key**: `AdminID`

2. **Customer**
    - **Attributes**: `CustomerID`, `Name`, `Email`, `Phone Number`, `Address`
    - **Primary Key**: `CustomerID`

3. **Product**
    - **Attributes**: `ProductID`, `Name`, `Category`, `Price`, `Stock`
    - **Primary Key**: `ProductID`

4. **Order**
    - **Attributes**: `OrderID`, `CustomerID`, `Date`, `Total Amount`, `Status`
    - **Primary Key**: `OrderID`
    - **Foreign Key**: `CustomerID`

5. **Order Details**
    - **Attributes**: `OrderID`, `ProductID`, `Quantity`, `Price`
    - **Primary Key**: `OrderID`, `ProductID`
    - **Foreign Keys**: `OrderID`, `ProductID`

6. **Payment**
    - **Attributes**: `PaymentID`, `OrderID`, `CustomerID`, `Payment Method`, `Payment Date`, `Amount`
    - **Primary Key**: `PaymentID`
    - **Foreign Keys**: `OrderID`, `CustomerID`


## Technology Stack

The E-Commerce Management System is built using a modern and efficient technology stack:

- **Java**: The primary programming language, known for its robustness and security.
- **NetBeans IDE**: A powerful IDE offering tools to streamline the development process.
- **MySQL**: The database management system, providing reliable data storage.
- **JDBC**: For seamless integration between the Java application and MySQL database.
- **AWS**: For scalable and secure cloud-based data storage.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

- **Atul Goyal**
