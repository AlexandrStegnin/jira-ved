FROM maven:3.8.6-amazoncorretto-19 AS builder
COPY ./ ./
RUN mvn clean package -DskipTests
FROM amazoncorretto:19
COPY --from=builder /target/jira-ved-1.0.jar /bot.jar
COPY ~/.local/share/caddy/certificates/acme-v02.api.letsencrypt.org-directory/bot.stegnin.com /usr/lib/jvm/java-19-amazon-corretto/lib/security/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bot.jar"]