# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
#LABEL maintainer=""

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/gcp-microservices-springboot-datastore-0.0.1-RELEASE

# Add the application's jar to the container
ADD ${JAR_FILE} gcp-microservices-springboot-datastore.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gcp-microservices-springboot-datastore.jar"]