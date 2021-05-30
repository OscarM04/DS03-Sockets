FROM maven:3.6.3-jdk-8 AS build
RUN mkdir app
WORKDIR /app
COPY . .
COPY ./src/main/resources/app.properties /app/properties/
RUN mvn clean install

#FROM openjdk:8-jre-alpine
#COPY --from=build /target/DS-Socket-1.0-SNAPSHOT.jar ./app
CMD ["java","-jar","./target/DS-Socket-1.0-SNAPSHOT.jar"]