FROM java:openjdk-8-jdk-alpine
VOLUME /tmp
ADD java-log-aggregation.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
