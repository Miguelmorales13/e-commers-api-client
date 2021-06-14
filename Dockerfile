FROM openjdk:16-alpine
WORKDIR /app
COPY . .
RUN ./mvnw package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/e-commers-0.0.1-SNAPSHOT.jar"]