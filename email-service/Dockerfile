FROM openjdk:11

RUN mkdir /opt/app
COPY target/email-service-*.jar /opt/app/app.jar

CMD ["java", "-jar", "/opt/app/app.jar"]
