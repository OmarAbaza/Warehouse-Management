FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean install -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/warehouse-0.0.1-SNAPSHOT.jar warehouse.jar
ENTRYPOINT ["java", "-jar","warehouse.jar"]
EXPOSE 8081