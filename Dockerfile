FROM maven:3.8.3-openjdk-17 AS builder
COPY ./ ./
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=builder /target/jira-ved-1.0.jar /bot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bot.jar"]