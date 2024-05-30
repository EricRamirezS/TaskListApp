# Project Setup Instructions

## Prerequisites
- **Git**: To clone the repository.
- **PostgreSQL**: To set up the database.
- **Java 21**: To run the backend.
- **Maven**: To manage the backend dependencies.
- **Node.js and npm**: To run the frontend.

## 1. Clone the Repository
First, clone the repository from GitHub to your local machine.
```sh
git clone https://github.com/EricRamirezS/TaskListApp.git
cd TaskListApp
```
## 2. Set Up the Database
Next, set up the PostgreSQL database using the provided SQL script.

1. Install PostgreSQL if you haven't already. Refer to the PostgreSQL installation guide for instructions specific to your operating system.
2. Create the Database:
    - Access the PostgreSQL command line interface::
      ```powershell
      psql
      ```
    - Execute the SQL script to create the tasksDB database:
      ```powershell
      \i /path/to/repository/database.sql
      ```
    - Exit the PostgreSQL command line interface:
      ```powershell
      \q
      ```
## 3. Launch the Backend
Navigate to the Backend directory and set up the Spring Boot application.

1. Navigate to the Backend Directory:
    ```powershell
    cd Backend
    ```
2. Run the Application in the default (dev) profile:
    - Linux
    ```powershell
    ./mvnw spring-boot:run
    ```
    - Windows

    ```powershell
    mvnw spring-boot:run
    ```
The application will connect to the tasksDB database on localhost:5432 with the user postgres and password postgres. Also it will use `sample_key` as API KEY

3. For Production Profile:

Set the required environment variables:
```properties
ENVIRONMENT_PLATFORM=prod
DB_USER=<your_db_user>
DB_HOST=<your_db_host>
DB_PORT=<your_db_port>
DB_NAME=<your_db_name>
DB_SSLM=<your_db_ssl_mode>
API_KEY=<Your_secret_api_key>
```

4. Swagger Documentation:
Access the Swagger UI at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

5. Running Test:
To run tests and generate a report, use the following command:
    - Linux
    ```powershell
    ./mvnw surefire-report:report
    ```
    - Windows

    ```powershell
    mvnw surefire-report:report
    ```
    View the test report at TaskListApp/Backend/target/site/surefire-report.html.

    
## 4. Launch the Frontend
Navigate to the frontend directory and set up the React application.

1. Navigate to the Frontend Directory:
    ```sh
    cd frontend
    ```
2. Install Dependencies:
    ```sh
    npm install
    ```
3. Set Environment Variables:
    Optionally, set the API URL and API Key, the default value will be used if environment variables are missing:
        ```properties
        VITE_API_URL=<backend_url> default value:'http://127.0.0.1:8080/api/tasks'
        VITE_API_KEY=<your_secret_api_key> default value:'sample_key'
        ```
4. Run the Application:
    ```sh
    npm run dev
    ```
    The frontend application will be accessible at [http://localhost:5173](http://localhost:5173).

## Summary of Environment Variables
### Backend (dev):
- Database: tasksDB on localhost:5432
- User: postgres
- Password: postgres
- API Key: sample_key
    
### Backend (prod):

- Environment Variables:
    - ENVIRONMENT_PLATFORM=prod
    - DB_USER=<your_db_user>
    - DB_HOST=<your_db_host>
    - DB_PORT=<your_db_port>
    - DB_NAME=<your_db_name>
    - DB_SSLM=<your_db_ssl_mode>
    - API_KEY=<your_api_key>
### Frontend:
- Environment Variables:
    - VITE_API_URL=(optional, default: `http://127.0.0.1:8080/api/tasks`)
    - VITE_API_KEY=(optional, default: `sample_key`)
