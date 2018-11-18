🔑 Authentication service 🔑
=========================================================================================================

## A Basic service to provide user authentication. 
Whenever a request is successful, a JWT token will be returned.

The service supports DB authentication, but is designed to add more auth methods (i.e SAML, Google OAuth, Facebook OAuth, etc)
by only adding the logic.

## Compile and run

With the maven wrapper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t auth-service .`
2) `docker run -p 8300:8300 auth-service`

## URLs

- Swagger

http://localhost:8300/swagger-ui.html

## Users

For development or local envs, this service works with an in-memory DB (h2).
The users are automatically saved in the DB and the script is `data.sql` file.

## Test
The user for testing is: 

username: serveradmin 

password: blast4321
