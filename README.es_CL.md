# Instrucciones de configuración del proyecto

## Requisitos previos
- **Git**: Para clonar el repositorio.
- **PostgreSQL**: Para configurar la base de datos.
- **Java 21**: Para ejecutar el backend.
- **Maven**: Para gestionar las dependencias del backend.
- **Node.js y npm**: Para ejecutar el frontend.

## 1. Clonar el repositorio
Primero, clone el repositorio de GitHub a su máquina local.
```sh
git clone https://github.com/EricRamirezS/TaskListApp.git
cd TaskListApp
```
## 2. Configurar la base de datos
A continuación, configure la base de datos PostgreSQL utilizando el script SQL proporcionado.

1. Instale PostgreSQL si aún no lo ha hecho. Consulte la guía de instalación de PostgreSQL para obtener instrucciones específicas para su sistema operativo.
2. Cree la base de datos:
 - Acceder a la interfaz de línea de comandos de PostgreSQL::
 ```powershell
 psql
 ```
 - Ejecute el script SQL para crear la base de datos TaskDB:
 ```powershell
 \i /ruta/al/repositorio/base de datos.sql
 ```
 - Salga de la interfaz de línea de comandos de PostgreSQL:
 ```powershell
 \q
 ```
## 3. Inicie el Back-end
Navegue hasta el directorio Backend y configure la aplicación Spring Boot.

1. Navegue hasta el directorio backend:
    ```powershell
    cd Backend
    ```
2. Ejecute la aplicación en el perfil predeterminado (dev):
    - Linux
    ```powershell
    ./mvnw spring-boot:run
    ```
    - Windows

    ```powershell
    mvnw spring-boot:run
    ```
La aplicación se conectará a la base de datos TaskDB en localhost:5432 con el usuario postgres y la contraseña postgres. También utilizará `sample_key` como API KEY

3. Para perfil de producción:

Establezca las variables de entorno requeridas:
```properties
ENVIRONMENT_PLATFORM=prod
DB_USER=<your_db_user>
DB_HOST=<your_db_host>
DB_PORT=<your_db_port>
DB_NAME=<your_db_name>
DB_SSLM=<your_db_ssl_mode>
API_KEY=<Your_secret_api_key>
```

4. Documentación Swagger :
Acceda a la interfaz de usuario de Swagger en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

5. Testing de ejecución:
Para ejecutar pruebas y generar un informe, utilice el siguiente comando:
    - Linux
    ```powershell
    ./mvnw surefire-report:report
    ```
    - Windows

    ```powershell
    mvnw surefire-report:report
    ```
 Vea el informe de prueba en TaskListApp/Backend/target/site/surefire-report.html.


## 4. Inicie el Front-end
Navegue hasta el directorio frontend y configure la aplicación React.

1. Navegue al directorio frontend:
    ```sh
    cd frontend
    ```
2. Instalar dependencias:
    ```sh
    npm install
    ```
3. Establecer variables de entorno:
 Opcionalmente, configure la URL de la API y la clave de API; se utilizará el valor predeterminado si faltan variables de entorno:
        ```properties
        VITE_API_URL=<backend_url> default value:'http://127.0.0.1:8080/api/tasks'
        VITE_API_KEY=<your_secret_api_key> default value:'sample_key'
        ```
4. Ejecute la aplicación:
    ```sh
    npm run dev
    ```
 Se podrá acceder a la aplicación frontend en [http://localhost:5173](http://localhost:5173).

## Resumen de variables de entorno
### Backend (desarrollador):
- Base de datos: taskDB en localhost:5432
- Usuario: postgres
- Contraseña: postgres
- Clave API: `sample_key`

### Backend (producción):

- Variables de entorno:
 - ENVIRONMENT_PLATFORM=prod
 - DB_USER=<tu_usuario_db>
 - DB_HOST=<tu_db_host>
 - DB_PORT=<tu_puerto_db>
 - DB_NAME=<tu_db_name>
 - DB_SSLM=<su_modo_ssl>
 - API_KEY=<su_api_key>
### Front-end:
- Variables de entorno:
 - VITE_API_URL=(opcional, predeterminado: `http://127.0.0.1:8080/api/tasks`)
 - VITE_API_KEY=(opcional, predeterminado: `sample_key`)
