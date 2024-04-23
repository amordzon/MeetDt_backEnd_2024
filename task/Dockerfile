FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM amazoncorretto:17.0.8-alpine3.18
WORKDIR /app
COPY --from=build /app/build/libs/task-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","task-0.0.1-SNAPSHOT.jar"]