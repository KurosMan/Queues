FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} EchoApplication.jar
ENTRYPOINT ["java","-jar","/EchoApplication.jar"]
