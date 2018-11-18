🚎 Itinerary service 🚎
============================================================================================================================

## Service to manage the cities and the itineraries.

Provides the APIs and the vaildations to create and read the cities and the itineraries.

Expose an API that given a departure city, show the itineraries, as a list, including the arrival and departure time, the number of 
stops and the destination.

## Compile and run

With the maven wraper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t itinerary-service .`
2) `docker run -p 8100:8100 itinerary-service`

## URLs

Running locally, use the Swagger URL

- Swagger

http://localhost:8300/swagger-ui.html


## Itineraries and cities

For development or local envs, this service works with an in-memory DB (h2).
The itineraries and cities are automatically saved in the DB and the script is `data.sql` file.

## Test
- Http://localhost:8100/itineraries/Rosario
- Http://localhost:8100/itineraries/ROS
