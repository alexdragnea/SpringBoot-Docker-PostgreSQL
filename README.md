# SpringBoot-Docker-PostgreSQL

## Dockerfile

```
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/Docker-Demo-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} Docker-Demo-0.0.1-SNAPSHOT.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","Docker-Demo-0.0.1-SNAPSHOT.jar"
```

## Running the application

First, we need to package the application, ```mvn clean install``` and after that ```mvn package```.The .jar file will be located under target directory and it will be used in the Dockerfile.

In order to run the application, make sure you have one instance of PostgreSQL running on the configurable port from  ```application.properties```, a database and the entity table according to ```ThemeParkRide.class``` or just simply add ```spring.jpa.hibernate.ddl-auto=update``` inside ```application.properties```.
