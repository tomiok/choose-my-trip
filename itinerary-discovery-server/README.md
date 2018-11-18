🌎 Discovery server 🌎
========================================================================================

### Discovery server, based on Eureka, for locating the services by name and avoid hardcoding URLs and ports to microservices communication.

It provides us some functionalities like monitoring, load balancing, client discovery and so on.

## Compile and run

With the maven wraper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t itinerary-discovery-server .`
2) `docker run -p 8761:8761 itinerary-discovery-server`

## URLs

- dashboard

http://localhost:8791/eureka/

- configs

http://localhost:8761/eureka/apps

http://localhost:8761/eureka/apps/{appId}
