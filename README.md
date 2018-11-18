✈️ Itinerary finder ✈️
======================================================================================
### A couple of microservices to find the shortest way in time and in number of stops.

### Providing a City identifier or a City name, the API will expose the itineraries ordered ASC for the time that the trip takes and for the number of the stops.

------------------------------------------------------------------------------------

## Technology stack
* Java 8
* Maven 3.3.x
* Spring Boot 2.1.0.RELEASE as base framework.
* Spring Cloud for Service registration, microservice communication with [Feign](https://github.com/OpenFeign/feign) and [Hystrix](https://github.com/Netflix/Hystrix), proving as a circuit breaker pattern and a super-easy/super-friendly way to write HTTP clients.
* [Zuul](https://github.com/Netflix/zuul) reverse proxy as API gateway
* Simple [JWT](https://jwt.io) authentication.
* Docker / Docker compose.
* Lombok to avoid some boilerplate code.
* JUnit with AssertJ and Mockito
* Swagger for the API documentation


------------------------------------------------------------------------------------


## How to use it with docker & docker-compose
- Run this command in your local machine - only docker and docker compose are needed to run locally in development mode.

`docker-compose up --build -d` 


(-d is to run detached in the console)


## How to use it as stand-alone jars
If you want to debug separately a service or do not want to run all the services as containers, you can run the services on its own, with the following simple steps.

1) `cd itinerary-discovery-server` => `./mvnw clean verify && ./mvnw spring-boot:run`

2) `cd trip-selector-service` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`

3) `cd itinerary-service` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`

4) `cd auth-service` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`

5) `cd zuul-gateway` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`


## APIs

After running the *docker-compose* command or as individual jars, you can go through the following URLs:

- http://localhost:8761 ==> And see all the registerd services

- http://localhost:8200/swagger-ui.html ==> Trip finder/selector service

- http://localhost:8200/swagger-ui.html ==> Itinerary service

- http://localhost:8300/swagger-ui.html ==> Authentication service


All services running - Eureka dashboard looks like this img: 

![alt text](https://user-images.githubusercontent.com/11444365/48674949-17cc7400-eb31-11e8-881d-c21365ec3a6e.png)

------------------------------------------------------------------------------------
## API forwarding by Zuul

This are the mappings in Zuul gateway, by service id.

| service id             | URL mapping            |
| :--------------------- | :--------------------- |
| trip-selector-service  | /trips-selector-api/** |
| auth-service           | /auth/**               |

------------------------------------------------------------------------------------

## Basic authentication - JWT based

### Get a JWT with user and password
``
curl -X POST \
  http://localhost:8081/auth/db \
  -H 'Content-Type: application/json' \
  -d '{
  "password": "blast4321",
  "username": "serveradmin"
}'
``
(This is the only user saved in the database *username:serveradmin & password:blast4321*)


------------------------------------------------------------------------------------

## Test the service

``
curl -X GET \
  http://localhost:8081/trips-selector-api/itineraries-finder/ROS \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXJ2ZXJhZG1pbiIsImlhdCI6MTU0MjQ5NTYzOCwianRpIjoiYjlhOTQwZjItMDBhMS00MWY2LTk1MzktM2M5YTIyM2I5ZTVlIn0.xkc4fipaWWIElauAgrnNlVRjMF2SaExQE_6xvlgxxed6JL6zjaohSyrc9h5_dxqw9N5x-UuZCfJsrrMNvnsPZQ' \
  -H 'cache-control: no-cache'
``

Explaining the args:

1) `http://localhost:8081/trips-selector-api/itineraries-finder/{city-identificator}` Is the URL that Zuul is going to forward. The city identificator is the departure city, in this case `ROS` or `Rosario` could be used.
2) `GET` is the HTTP verb used.
3) Headers: Authorization with the bearer token, in this case a JWT token. As the [RFC 6750](https://tools.ietf.org/html/rfc6750). You can replace (you should do that) With the one taken in the authentication service.

------------------------------------------------------------------------------------

## Room for improvement

The JWT flow is not production ready. 
A better approach is to externalize the main security condfigs like signature algorithm and the secret key in a secured config server and not hardcoded in the code (and in plain text).

There are some repeated dependencies across the POMs. A good approach could be to have a shaerd project as a parent POM with these shared dependencies and import the parent, in order to avoid versions mismatch, classloader errors, communication problems between the frameworks, etc.

Databases are also not production ready. For upper environments, it must be replaced in the project the in memory database *h2* for a relational database like MySQL or PostgreSQL.
**[HikariCP](https://github.com/brettwooldridge/HikariCP)** should be used as connection pooling framework because of it is a lightweight and lightning JDBC CP framework and a standard for Springboot 2.x.x.  
