FROM openjdk:8-jre-slim
COPY ./build/libs/TrackOn-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-Xmx1024m", "/app.jar"]
EXPOSE 7888