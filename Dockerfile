FROM maven:3.8.6-amazoncorretto-19 AS builder
COPY ./ ./
RUN mvn clean package -DskipTests
FROM amazoncorretto:19
COPY --from=builder /target/jira-ved-1.0.jar /bot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bot.jar"]