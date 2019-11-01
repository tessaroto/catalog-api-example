# Catalog Services

Service responsible for managing product

### Database
As database was used Postgresql

## Development
### Requirements
- Minimum java version 1.8.
- Redis
- Docker CE is optional.


### Building
```sh
./mvnw clean install
```
### Starting application
```sh
redis-server
./mvnw spring-boot:run
```
The application will listening on 8080, if you prefer you can use the Swagger.
http://localhost:8080/swagger-ui.html

### Run tests
```sh
./mvnw test
```
### Docker
if you prefer you can use the Docker for running the application.

#### Docker Compose
In project directory, execute the command to start the stack.
```sh
docker-compose up
```
