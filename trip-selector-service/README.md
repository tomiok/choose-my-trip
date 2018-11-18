🗺️ Trip selector service 🗺️
===========================================================================================================================

## This service provides sorting functionality. Shows the itineraries in 2 lists.
- One ordered by number of stops
- One ordered by itinerary duration
Using the data allocated in **itinerary-service**

## Compile and run

With the maven wrapper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t trip-selector-service .`
2) `docker run -p 8200:8200 trip-selector-service`

## URLs

Running locally, use the Swagger URL

- Swagger

http://localhost:8200/swagger-ui.html

# Test

- http://localhost:8200/itineraries-finder/ROS
- http://localhost:8200/itineraries-finder/Rosario
