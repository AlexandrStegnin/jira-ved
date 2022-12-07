FROM maven:3.8.6-amazoncorretto-19 AS builder
COPY ./ ./
RUN mvn clean package -DskipTests
FROM amazoncorretto:19
COPY --from=builder /target/jira-ved-1.0.jar /bot.jar
FROM jordi/ubuntu
RUN apt-get update
# installing an editor is not necessary, but is handy
RUN apt-get -y install nano

# openssl is the only required thing to install
RUN apt-get -y install openssl
RUN openssl s_client -showcerts -connect ${SITE_HOST}:${SITE_PORT} </dev/null | sed -n -e '/-.BEGIN/,/-.END/ p' > ${SITE_HOST}.pem
RUN keytool -import -noprompt -trustcacerts -alias ${SITE_HOST}.alias -file ${SITE_HOST}.cert \
        -keystore /usr/lib/jvm/java-19-amazon-corretto/lib/security/ -storepass ${KEYSTORE_PASS}
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bot.jar"]