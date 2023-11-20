## Komet Sales Exercise with Rest API

### The app use the following technologies:

- Java 21
- IntelliJ IDEA
- Spring Boot 3
- JUnit 5
- Swagger
- Docker
- MySql (Docker Image)
- Gradle

### Notes

This project optionally depends on having docker/docker-compose available on your system.

It requires an external network names "docker-network" of type "bridge" to be available. You can create this by:

```
docker network create docker-network
docker compose -f infra.yml -p exercise start mysql-server
```

the docker is for mysql DB (you can also install mysql locally) after install/run the script ```data.sql``` script.

### Install and run Local:

- `gradle clean`
- `gradle build`
- `gradle bootRun`

### Use

After start the project open the  [Swagger API](http://localhost:8080/swagger-ui.html).
