FROM maven:3.9.16-amazoncorretto-17-debian-trixie AS builder
WORKDIR /app
COPY pom.xml .
COPY EcommerceAPI ./EcommerceAPI
RUN mvn -DskipTests package

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]




