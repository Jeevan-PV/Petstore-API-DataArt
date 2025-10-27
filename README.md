## PetStore API Automated Tests

This repository contains automated API tests written in **Java** using **RestAssured** and **TestNG** to validate the core CRUD (Create, Read, Update, Delete) operations on the public Swagger PetStore API.

* * *

### 🛠️ Prerequisites

Before running the tests, ensure you have the following installed:

-   **Java Development Kit (JDK) 8+**
    
-   **Maven** (for dependency management)
    
-   An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
    

* * *

### ⚙️ Setup and Dependencies

This project uses Maven to manage dependencies. The required libraries are configured in the `pom.xml` file:

| **Library** | **Version** | **Description** |
| --- | --- | --- |
| `rest-assured` | Latest Stable | HTTP Client for API interaction. |
| `testng` | Latest Stable | Test execution framework. |
| `json-path` | (Implicit with RestAssured) | JSON parsing and validation. |

#### Cloning the Repository

Bash

    git clone <your-repository-url>
    cd petstore-api-tests

* * *

### 🚀 How to Run Tests

You can run the tests using your IDE or via the command line with Maven.

#### 1\. Running via IDE (Recommended)

1.  Open the project in your IDE.
    
2.  Navigate to the test class: `com.petstore.api.tests.UserCRUDTest.java`.
    
3.  Right-click on the class name and select **"Run 'UserCRUDTest'"** (or equivalent option).
    

#### 2\. Running via Command Line (Maven)

Execute the following commands from the root directory of the project:

Bash

   1. mvn clean
   2. mvn compile
   3. mvn test
    

* * *

### 🧪 Test Structure

The main test file is `UserCRUDTest.java`, which covers the full lifecycle of a user resource:

| **Test Method** | **Priority** | **Endpoint** | **Operation** | **Description** |
| --- | --- | --- | --- | --- |
| `testCreateUser_POST` | 1 | `/user` | `POST` | Creates a new user with a unique username. |
| `testReadUser_GET` | 2 | `/user/{username}` | `GET` | Verifies the newly created user exists. |
| `testUpdateUser_PUT` | 3 | `/user/{username}` | `PUT` | Updates the user's details (e.g., `firstName`). |
| `testReadUserAfterUpdate_GET` | 4 | `/user/{username}` | `GET` | **(Should be added)** Confirms the update was successful. |
| `testDeleteUser_DELETE` | 5 | `/user/{username}` | `DELETE` | Removes the user resource. |
