↪️ zuul-gateway ↪️
========================================================================================================================

## API gateway using zuul, provinding routing and security for the APIs. The URLs in this service are only visible by this 
entry point and no one else can reach the services without passing through the API gateway.

## Compile and run

With the maven wrapper:

1) `./mvnw clean verify`
2) `./mvnw spring-boot:run`

With docker

1) `docker build -t zuul-gateway .`
2) `docker run -p 8081:8081 zuul-gateway`

## Routing table
### This service provide a basic routing and a load balancer (Ribbon) to access the services.

This are the mappings in Zuul gateway, by service id.

| service id             | URL mapping            |
| :--------------------- | :--------------------- |
| trip-selector-service  | /trips-selector-api/** |
| auth-service           | /auth/**               |
