FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} QueuesProject.jar
ENTRYPOINT ["java","-jar","/QueuesProject.jar"]
