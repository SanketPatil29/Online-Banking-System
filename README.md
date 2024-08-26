# Online Banking System

## Overview
This project is an Online Banking System built using a microservice architecture. It includes various services for user management, account management, and notifications, each of which operates independently and communicates with others via REST APIs. The project leverages modern DevOps practices, including containerization, continuous integration and continuous deployment (CI/CD), and automated deployment, to ensure a robust, scalable, and maintainable system.

## Video Demo
Video demo of the Online Banking System [link](https://drive.google.com/file/d/1voC-7WOnH4rpR1oCbalbXsVwA_Z_l3n5/view?usp=drive_link).

## Tech Stack

### Frontend
- **React.js**: This JavaScript library is used for building the user interface of the Online Banking System. React.js allows for the creation of reusable UI components, making the development process more efficient and the application more responsive. It handles the view layer for the application, providing a seamless user experience with dynamic content updates without requiring a full page reload.

### Backend
- **Spring Boot**: This framework is used to develop the backend services. Spring Boot simplifies the development of production-ready applications by providing defaults for configuration and setup. It is chosen for its ability to create stand-alone, production-grade Spring-based applications with minimal configuration. Each microservice in this project is a Spring Boot application, ensuring a modular and scalable backend.

### Database
- **MySQL**: This relational database management system (RDBMS) is used for storing user and account data. MySQL is known for its reliability, security, and ease of use. It supports ACID transactions, which ensures data integrity and supports complex queries to manage and retrieve data efficiently.

## Microservices

### UserService
- **Features**:
  - **User Registration**: This feature allows new users to register by providing necessary details such as name, email, and password. Upon successful registration, a new user profile and a corresponding account are created.
  - **User Login**: Enables users to log in to the system using their credentials. 
  - **View Profile**: Allows users to view their profile information, including personal details and account information. 
  - **Account Creation**: When a new user registers, an account is automatically created for them. This feature handles the initial setup of the user’s financial accounts in the system.

### AccountService
- **Features**:
  - **View Accounts**: Provides users with the ability to view all their accounts. This includes details such as account type, balance, and recent transactions.
  - **Transaction Details**: Users can view detailed information about their transactions, including date, amount, transaction type, and status. This feature helps users keep track of their financial activities.
  - **Money Transfer**: Enables users to transfer money to other users' accounts within the system. This includes functionality for specifying the recipient, amount, and ensuring secure transactions.

### NotificationService
- **Features**:
  - **Email Notifications**: Sends an email notification to the sender’s email address for each successful money transfer. This feature ensures that users are promptly informed about their transaction status, enhancing security and transparency.

## DevOps Practices

### Containerization
- **Docker**: Docker is used to containerize the microservices, ensuring that each service runs in a consistent environment. This approach eliminates the "it works on my machine" problem by bundling the application and its dependencies into a single container. Docker simplifies the deployment process, improves scalability, and enhances resource utilization by running each microservice in isolated containers.

### Continuous Integration and Continuous Deployment (CI/CD)
- **Jenkins**: Jenkins is used to automate the build and deployment pipeline. With Jenkins, every code change goes through a series of automated tests and builds, ensuring that the codebase remains stable and deployable. The CI/CD pipeline includes steps for building Docker images, running unit and integration tests, and deploying the application to the staging or production environment. This automation speeds up development cycles and reduces the risk of human error.

### Deployment
- **Ansible**: Ansible is used for the automated deployment of services. Ansible playbooks define the configuration and deployment steps for each microservice, ensuring a repeatable and reliable deployment process. Ansible manages server configurations, application deployment, and orchestration tasks, making it easier to deploy and scale the application across multiple environments.

### Container Orchestration
- **Docker Compose**: Docker Compose is used for orchestrating multi-container Docker applications. It allows defining and running multi-container Docker applications with a simple YAML file. Docker Compose manages the interdependencies and communication between microservices, making it easy to deploy and manage the entire application stack locally or in production environments.

### Monitoring and Logging
- **ELK Stack (Elasticsearch, Logstash, Kibana)**: The ELK stack is used for logging and monitoring the application. 
  - **Elasticsearch**: Stores and indexes log data for efficient search and retrieval.
  - **Logstash**: Collects, processes, and forwards log data to Elasticsearch. It supports data filtering, transformation, and enrichment.
  - **Kibana**: Provides a powerful interface for visualizing log data and monitoring application performance. With Kibana, users can create dashboards, set up alerts, and analyze logs to gain insights into application behavior and troubleshoot issues.

These DevOps practices ensure that the Online Banking System is robust, scalable, and easy to maintain, providing a seamless experience for end-users and simplifying the management of the application's infrastructure.
