# todoApp-by-chatgpt

ChatGPT-TodoAPP is a simple todo list application developed using Spring Boot, Hibernate, and MySQL.
Creates a RESTful API to manage a simple todo list application using Spring Boot, Hibernate, and MySQL. The application should allow users to create, read, update, and delete todo items. Each item should have a title and a description. Use Hibernate to persist the items in the database.

## Requirements

- Java Development Kit (JDK) 8 or higher
- MySQL

## Installation and Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/ChatGPT-TodoAPP.git
    cd ChatGPT-TodoAPP
    ```

2. **Database Setup:**

    - Create a MySQL database named `todo`.
    - Update the `application.properties` file with your database connection details:
    
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/todo
        spring.datasource.username=root
        spring.datasource.password=root
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
        ```

3. **Run the application:**

    ```bash
    ./mvnw spring-boot:run
    ```

    Or if you have Maven installed globally:

    ```bash
    mvn spring-boot:run
    ```

4. **Access the application:**

    Once the application is running, you can access it at [http://localhost:8080](http://localhost:8080) in your web browser.

## API Endpoints

The following RESTful endpoints are available:

- **GET /api/todo**: Get all todo items
- **GET /api/todo/{id}**: Get a todo item by ID
- **POST /api/todo**: Create a new todo item
- **PUT /api/todo/{id}**: Update an existing todo item
- **DELETE /api/todo/{id}**: Delete a todo item by ID

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


- Was it easy to complete the task using AI? --Very easy, fast and convenient
- How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics)  -- 20-30mins in total
- Was the code ready to run after generation? What did you have to change to make it usable? -- The main code was generated without errors but testing code needed adjustment.
- Which challenges did you face during completion of the task? -- Mainly very smooth process with no difficulties, just had to specify to provide test for every methods and without any errors.
- Which specific prompts you learned as a good practice to complete the task?
 -- "pls represent the tree structure of the project to demonstrate in what new packages should be created for this classes"
 -- "pls provide tests for the each method of the TodoItemControlle"
