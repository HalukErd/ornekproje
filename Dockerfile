FROM openjdk:11
WORKDIR /app/
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} application.jar
COPY data.sql data.sql
ENTRYPOINT ["java", "-jar", "application.jar"]