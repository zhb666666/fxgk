FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/safety-management-system-1.0.0.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/safety_management
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
