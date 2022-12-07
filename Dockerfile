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
RUN openssl s_client -connect ${SITE_HOST}:${SITE_PORT} </dev/null \
    | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ${SITE_HOST}.cert \
    keytool -import -noprompt -trustcacerts -cacerts -alias ${SITE_HOST}.alias -file ${HOST}.cert \
        -keystore /usr/lib/jvm/java-19-amazon-corretto/lib/security/ -storepass ${KEYSTORE_PASS}
COPY ~/.local/share/caddy/certificates/acme-v02.api.letsencrypt.org-directory/bot.stegnin.com /usr/lib/jvm/java-19-amazon-corretto/lib/security/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bot.jar"]