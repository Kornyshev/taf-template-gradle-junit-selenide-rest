# Test Automation Framework

This project is a Test Automation Framework designed with Java, Selenide, RestAssured, and managed with Gradle. It serves as an educational tool to showcase a potential structure and implementation of a test automation framework. The primary goal is to demonstrate the framework's appearance and functionality, featuring a project structure suitable for testing GitHub's UI/API levels. This framework includes 6 demonstrative tests to showcase interaction capabilities, utilizing JUnit 5 extension mechanisms for setting up test preconditions through the API. Additionally, Allure reporting is integrated for enhanced test result visualization.

## Features

- **Technologies**: Leveraging Java for programming, Selenide for web UI testing, RestAssured for API testing, and Gradle for build management.
- **Test Coverage**: Includes 6 tests demonstrating UI/API interaction with GitHub.
- **JUnit 5 Extensions**: Utilizes JUnit 5 for creating test preconditions via API.
- **Allure Reporting**: Integrated for improved test reporting and visualization.

## Configuration Highlights

The project is set up with the following key configurations:

### Plugins

- Application plugin for Gradle project configuration.
- Allure Gradle plugin (version 2.11.2) for test reporting.

### Dependencies

- AspectJ Weaver for AOP support.
- Allure libraries for JUnit 5 integration and reporting.
- JUnit Jupiter for unit testing.
- Lombok for reducing boilerplate code.
- Selenide for simplified web UI testing.
- RestAssured for RESTful API testing.
- Jackson Databind for JSON parsing.
- Awaitility for asynchronous testing.
- Logback and SLF4J for logging.
- AssertJ for fluent assertion syntax.

### Java Toolchain

- Configured to use Java 17, ensuring compatibility with modern Java features.

### Test Configuration

- Tests are set to ignore failures, allowing for comprehensive test runs regardless of individual test outcomes.
- JVM arguments include the AspectJ Weaver agent for runtime weaving, necessary for certain Allure functionalities.

## Usage

To execute the tests, ensure Java 17 is set up in your environment. Gradle commands facilitate the compilation and execution of tests, making use of the Java toolchain configuration for Java 17. This framework is designed to be both comprehensive for learning purposes and practical for application in real-world testing scenarios.

### gradle clean test allureReport