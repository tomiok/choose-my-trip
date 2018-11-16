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

------------------------------------------------------------------------------------


## How to use it
- Run this command in your local machine - only docker and docker compose are needed to run locally in development mode.

`docker-compose --build -d` 


(-d is to run detached in the console)

## APIs

After running the *docker-compose* command, you can go through the following URLs:

- http://localhost:8761 ==> And see all the registerd services
- http://localhost:8200/swagger-ui.html ==> Trip finder/selector service
- http://localhost:8200/swagger-ui.html ==> Itinerary service
