FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/book-master-0.0.1-SNAPSHOT.jar book-master.jar

ENTRYPOINT ["java", "-jar", "book-master.jar"]