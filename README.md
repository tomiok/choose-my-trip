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
* Simple [JWT](https://jwt.io) authentication.
* Docker / Docker compose.
* Lombok to avoid some boilerplate code.
* Zalando Problem library for better and cleaner exception handling.
* Swagger for the API documentation

------------------------------------------------------------------------------------


## How to use it with docker & docker-compose
- Run this command in your local machine - only docker and docker compose are needed to run locally in development mode.

`docker-compose --build -d` 


(-d is to run detached in the console)


## How to use it as stand-alone jars
If you want to debug separately a service or do not want to run all the services as containers, you can run the services on its own, with the following simple steps.

1) `cd itinerary-discovery-server` => `./mvnw clean verify && ./mvnw spring-boot:run`
2) `cd trip-selector-service` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`
3) `cd itinerary-service` => `./mvnw clean verify && export SPRING_PROFILES_ACTIVE=local && ./mvnw spring-boot:run`


## APIs

After running the *docker-compose* command or as individual jars, you can go through the following URLs:

- http://localhost:8761 ==> And see all the registerd services
- http://localhost:8200/swagger-ui.html ==> Trip finder/selector service
- http://localhost:8200/swagger-ui.html ==> Itinerary service
