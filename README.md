# Restaurant Management System

This Restaurant Management System is a Java-based application designed to help restaurant administrators efficiently manage daily operations, including menu management, order processing, and staff management. The system uses SQL database connectivity for secure and reliable data storage and retrieval.

## Features

### 1. Admin Login
- **Description:** Provides a secure login interface for administrators. Only authenticated users can access the system's features.
- **Purpose:** Ensures that sensitive operations are accessible only to authorized personnel.

### 2. Home Page
- **Description:** Acts as the central hub for navigating the system's functionalities.
- **Options Available:**
  - **Add Customer:** Register new customers in the system.
  - **View Orders:** View and manage customer orders.
  - **Manage Menu:** Update and maintain the restaurant's menu items.
  - **Manage Waiters:** Oversee and manage waiter details.
  - **Manage Orders:** Process and update the status of orders.
  - **Generate Bill:** Generate billing information for customers.
  - **Admin Login:** Redirects back to the login page for security reauthentication.

### 3. Menu Management
- **Description:** Allows the admin to add, update, and delete menu items with fields for Menu ID, Menu Name, and Price.
- **Purpose:** Ensures easy and organized updates to the restaurant's menu.

## Technologies Used

- **Java**: Core programming language used for backend logic and GUI.
- **SQL (JDBC)**: Used for database connectivity and data management.
- **Java Swing**: Provides a simple and intuitive user interface for interaction.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/Restaurant-Management-System.git
   cd Restaurant-Management-System
2. **Set up the SQL Database:**
- Create a new database (e.g., restaurant_db).
- Import the provided SQL script (if available) to set up tables and initial data.
- Update database connection parameters in the Java code (e.g., database URL, username, password).

3. **Run the Project:**
- Open the project in an IDE (like Eclipse or IntelliJ).
- Run the main file to start the application.

## Database Schema
- **Admin Table:** Stores login credentials for administrators.
- **Menu Table:** Stores menu items with fields like Menu ID, Menu Name, and Price.
- **Order Table:** Stores information about customer orders.
- **Waiter Table:** Stores waiter details.

## Usage
- Login using admin credentials to access the system.
- Navigate to different features using the Home Page.
- Manage Menu to add, update, or delete items in the restaurant's menu.
- View Orders to monitor and update customer orders.
- Generate Bill to create bills for completed orders.

## Future Improvements
- Add detailed reports for sales and inventory.
- Implement customer feedback tracking.
- Add role-based access for different staff levels (e.g., cashiers, waiters).
- Enhance the UI for a more modern look and feel.
