FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY build/libs/coffeeshop-0.0.1-SNAPSHOT.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar", "--server.port=$PORT"]
