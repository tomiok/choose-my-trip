FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
COPY target/*.jar app.jar
# touch the archive for timestamp
EXPOSE 8081
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]