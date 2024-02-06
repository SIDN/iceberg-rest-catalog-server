FROM alpine:3.19

# Set environment variables for configuration
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV MAVEN_HOME=/usr/share/maven

RUN apk add --no-cache openjdk21 maven

# Set default values for environment variables
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Add labels for better maintainability
LABEL maintainer="SIDN Labs"


# Set the working directory
WORKDIR /app

# Copy the application code to the container
COPY target/iceberg-restcatalog-server-0.0.1-SNAPSHOT.jar /app/iceberg-restcatalog-server.jar

# Run any necessary commands
#RUN mvn clean install

# Set the entrypoint command
CMD ["java", "-jar", "/app/iceberg-restcatalog-server.jar"]


#FROM openjdk:21-jdk-slim-bookworm
##MAINTAINER sidnlabs.nl
#COPY target/iceberg-restcatalog-server-0.0.1-SNAPSHOT.jar iceberg-restcatalog-server.jar
#ENTRYPOINT ["java","-jar","/iceberg-restcatalog-server.jar"]