# TP_AQL
# TP3: Integration Testing in Java (Part 1)
**Author:** Rezak Abderaouf  
**Course:** Software Quality Assurance (AQL)

## 🎯 Lab Overview
This repository contains the implementation of Part 1 of the Integration Testing lab. The goal of this TP was to practice writing integration tests using **Java, JUnit 5 (Jupiter), and Mockito**. The exercises progressed in complexity, focusing on testing interactions between modules, simulating database layers, and handling external API failures without relying on real infrastructure.

## 🏗️ Exercises Completed

* **Exercise 1: Simple Module Interaction**
    * Implemented `UserService` and `UserRepository`.
    * Used Mockito (`@Mock`) to simulate the repository layer and `verify()` to ensure the service delegates the retrieval correctly.
* **Exercise 2: Database Interaction with Mocks**
    * Implemented a layered architecture: `OrderController` -> `OrderService` -> `OrderDao`.
    * Used a combination of `@Mock` (for the DAO) and `Mockito.spy()` (for the Service) to verify the flow of execution from the controller all the way to the database layer.
* **Exercise 3: API Integration & Exception Handling**
    * Implemented `ProductService` interacting with an external `ProductApiClient`.
    * Tested three specific integration scenarios:
        1.  Successful data retrieval.
        2.  API downtime (`500 Internal Server Error`).
        3.  Incompatible data formats (`400 Bad Request`).
    * Utilized Mockito's `thenThrow()` and JUnit's `assertThrows()` to validate system resilience.

## 🛠️ Technical Stack
* **Java:** JDK 21 (LTS)
* **Testing Framework:** JUnit 5 (Jupiter) `5.10.2`
* **Mocking Framework:** Mockito `5.15.2` (with ByteBuddy `1.15.11`)
* **Build Tool:** Maven

---

## 🐛 Troubleshooting & Engineering Notes

During the execution of this lab, several environment and dependency challenges were encountered and resolved. Documenting them is crucial for future debugging:

### 1. The Java 25 vs. ByteBuddy Compatibility Issue
* **The Problem:** Mockito uses the *ByteBuddy* library under the hood to dynamically generate mock objects. The project was initially running on **Java 25 (Early Access)**. ByteBuddy crashed with an `IllegalArgumentException` because it does not yet officially support class file version 69.0 (Java 25).
* **The Solution:** 
  1. Upgraded Mockito to `5.15.2` and explicitly added ByteBuddy `1.15.11` to the `pom.xml`.
  2. Downgraded the project SDK and Maven compiler targets to **Java 21 LTS** (class file version 65.0) for enterprise stability.
  3. Encountered an `UnsupportedClassVersionError` because the `.class` files were still compiled in Java 25. Executed a clean rebuild (`Build -> Rebuild Project`) to clear the old bytecode, resulting in perfectly passing tests.

### 2. Maven Scope and Folder Structure
* **The Problem:** Annotations like `@Test` and `@InjectMocks` could not be resolved initially despite being in the `pom.xml`.
* **The Solution:** Identified that Maven's `<scope>test</scope>` tag restricts testing libraries exclusively to the `src/test/java/` directory. Moved the test classes out of `src/main/java/` into the correct testing directory, which instantly resolved the imports.

### 3. JUnit 4 vs. JUnit 5 Collision
* **The Problem:** Received an "Old style JUnit test method" warning and tests failed to execute properly.
* **The Solution:** Removed the legacy `import org.junit.Test;` (JUnit 4) and replaced it with `import org.junit.jupiter.api.Test;` (JUnit 5). Also added the `mockito-junit-jupiter` bridge dependency to fully integrate Mockito with the Jupiter engine via `@ExtendWith(MockitoExtension.class)`.

## 🚀 How to Run the Tests
To execute all integration tests, run the following Maven command in the terminal:
```bash
mvn clean test