<a href="https://github.com/IzaiasValentim/General-API">
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&duration=01&pause=1&color=8E84FF&background=26FFDE0F&vCenter=true&repeat=false&width=74&height=25&lines=%3C+home" alt="Typing SVG" />
</a>

  
<div align="center">
    <a href="https://www.linkedin.com/in/izaiasvalentim/"><img alt="LinkedIn" src="https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555"></a>
    <a href="https://github.com/IzaiasValentim/General-API/blob/dev/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/YousefIbrahimismail/Project-README-Template?color=ff69b4&style=for-the-badge"></a>
</div>
<br>

<!-- Project title 
* use a dynamic typing-SvG here https://readme-typing-svg.demolab.com/demo/
*
*  Instead you can type your project name after a # header
-->

<div align="center">
<a href="#"><img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=33&pause=1000&color=8E84FF&background=26FFDE0F&center=true&vCenter=true&width=435&lines=General+API!" alt="Typing SVG" /></a>
</div>

## Requirements & Getting start

### Key Technologies and Versions
- **Spring Boot**: Version `3.4.1`
- **Java**: Version `21`
- **Gradle**: Kotlin DSL-based build script (`build.gradle.kts`)

### Core Dependencies
The following dependencies are configured in the project:
- **Spring Boot Starter Web**: REST API development
- **Spring Boot Starter Data JPA**: Simplified database access
- **Spring Boot Starter Test**: Unit and integration testing
- **Spring Boot DevTools**: Development environment optimization
- **H2 Database**: In-memory database for local development
- **Spring Dotenv**: Environment variable management (`me.paulschwarz:spring-dotenv:4.0.0`)
- **Springdoc OpenAPI**: API documentation and Swagger UI (`springdoc-openapi-starter-webmvc-ui:2.7.0`) 

## Getting Started

### Prerequisites
Ensure the following are installed on your machine:
- Java `21` or higher;
- Gradle (optional; included with the wrapper)
- Git.

### Steps to Run the Project

1. **Clone the Repository**  
   Open your terminal and execute:
   ```bash  
   git clone https://github.com/IzaiasValentim/General-API.git 
   cd General-API
   ```
2. **Build the Project** 
    ```bash
    ./gradlew build  
    ```
3. **Run the Application**
    ```bash
    ./gradlew bootRun  
    ```
4. **Access the API**
   - By default, the application runs on: http://localhost:8081

5. **API Documentation**
   - Access the Swagger UI for exploring the API:
    http://localhost:8081/swagger-ui.html

### Additional Notes
- Environment Variables: The project uses spring-dotenv for managing environment variables. Configure .env files in the root directory for custom settings.
   - #### .env Model:
      - ACTUAL_PROF =
      - DB_URL =
      - DB_USER =
      - DB_PASSWORD =
- Database Configuration: The default configuration uses an in-memory H2 database. Modify the application-dev.yml file in src/main/resources to connect to a different database.

