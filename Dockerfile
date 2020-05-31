FROM gradle:6.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/goldspacesvc-1.0.0.jar /app/goldspacesvc-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/goldspacesvc-1.0.0.jar"]