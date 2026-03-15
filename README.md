
# Smart Contact Manager

Smart Contact Manager is a robust web application built with Spring Boot, designed to help users efficiently manage their personal and professional contacts. The platform offers secure authentication, role-based access, a modern UI, and a suite of features for contact management.

---

## Table of Contents
1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Tools & Libraries](#tools--libraries)
4. [Project Structure](#project-structure)
5. [Setup & Installation](#setup--installation)
6. [Configuration](#configuration)
7. [Usage](#usage)
8. [Customization](#customization)
9. [Testing](#testing)
10. [Contribution](#contribution)
11. [License](#license)
12. [Credits](#credits)

---


## Features
- User registration and login (Spring Security)
- OAuth2.0 authentication (Google, etc.)
- Email verification for new users
- Password encryption (BCrypt)
- Add, edit, delete, and search contacts
- Contact details: name, email, phone, image, description
- Role-based access control (Admin/User)
- Responsive UI with custom CSS and JavaScript
- Error handling and custom error pages
- Success and failure notifications
- User profile management
- Contact image upload and storage (Cloudinary)
- Search contacts by name/email
- Pagination for contact lists
- Secure session management
- Logout functionality
- Forgot password and password reset (optional)
- RESTful API endpoints (optional)
- Modular service, repository, and controller layers
- Email notifications (EmailService)
- Exception handling (ResourceNotFoundException)
- Logging and monitoring (optional)

---


## Tech Stack
- **Backend:**
   - Java 17+
   - Spring Boot
   - Spring Security
   - Spring Data JPA (Hibernate)
   - OAuth2.0 (Spring Security OAuth)
   - Maven
- **Frontend:**
   - Thymeleaf (template engine)
   - HTML5, CSS3, JavaScript (custom scripts)
   - Responsive design (mobile-friendly)
- **Database:**
   - MySQL (configurable in `application.properties`)
- **Cloud Storage:**
   - Cloudinary (for image uploads)
- **Testing:**
   - JUnit (unit/integration tests)
- **Build Tools:**
   - Maven Wrapper (`mvnw`, `mvnw.cmd`)

---


## Tools & Libraries
- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Data JPA
- Spring Security OAuth2 Client
- Hibernate ORM
- MySQL Connector/J
- BCrypt PasswordEncoder
- Cloudinary Java SDK
- EmailService (custom)
- ResourceNotFoundException (custom)
- Lombok (optional)
- Maven Surefire Plugin (testing)
- Logback (logging)

---

## Project Structure
- `src/main/java/com/scm/SCM/` — Java source code
   - Controllers: Handle web requests
   - Services: Business logic (UserServices, EmailService, etc.)
   - Repositories: Data access (JPA)
   - Entities: User, Contact, etc.
   - Helpers: Exception handling, utilities
- `src/main/resources/static/` — Static assets
   - `css/`: Stylesheets (input.css, output.css, style.css)
   - `js/`: JavaScript files (admin.js, contact_modal.js, script.js)
   - `images/`: User/contact images
- `src/main/resources/templates/` — Thymeleaf HTML templates
   - `base.html`, `navbar.html`, `home.html`, `login.html`, `user/` (user views)
- `src/main/resources/application.properties` — App configuration
- `src/test/java/com/scm/SCM/` — Test code
- `pom.xml` — Maven dependencies and build config

---

## Setup & Installation
1. **Clone the repository**
    ```bash
    git clone <repo-url>
    ```
2. **Build the project**
    ```bash
    ./mvnw clean install
    ```
3. **Run the application**
    ```bash
    ./mvnw spring-boot:run
    ```
4. **Access the app**
    Open [http://localhost:8080](http://localhost:8080) in your browser.

---

## Configuration
- Update `src/main/resources/application.properties`:
   - Database URL, username, password
   - Email SMTP settings (for EmailService)
   - Server port (default: 8080)
- Environment-specific configs in `application-dev.properties`

---

## Usage
- Register as a new user
- Verify email (if enabled)
- Log in with credentials
- Add new contacts with details and image
- Edit or delete existing contacts
- Search contacts by name/email
- Manage user profile
- Admin can manage users and contacts
- View error/success messages

---

## Customization
- Modify static files in `static/` for custom styles/scripts
- Update Thymeleaf templates for UI changes
- Extend entities/services for new features
- Configure database and email settings in properties files

---

## Testing
- Run unit and integration tests:
   ```bash
   ./mvnw test
   ```
- Test coverage for services, controllers, and repositories

---

## Contribution
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Submit a pull request
5. Follow code style and documentation guidelines

---

## License
This project is licensed under the MIT License.

---

## Credits
- Developed by [Your Name/Team]
- Powered by Spring Boot, Thymeleaf, and MySQL
- Inspired by modern contact management needs

---

> For any issues or feature requests, please open an issue in the repository.
