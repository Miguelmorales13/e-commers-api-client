FROM openjdk:16-alpine
WORKDIR /app
COPY . .
RUN chmod 777 mvnw && ./mvnw package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/e-commers-0.0.1-SNAPSHOT.jar"]
