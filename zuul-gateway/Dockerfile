# Slim jdk image
FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

# Copy and rename the jar
COPY target/*.jar app.jar

# Expose port 8081
EXPOSE 8081

# Touch the archive for timestamp
RUN sh -c 'touch /app.jar'

# Run java -jar command in the renamed jar
ENTRYPOINT ["java","-jar","/app.jar"]