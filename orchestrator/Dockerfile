# Stage 1 : Build a native artifact
FROM quay.io/quarkus/ubi-quarkus-native-image:21.2.0-java11 AS build
COPY . /code/
USER root
WORKDIR /code
RUN ./mvnw -pl shared-resources install
RUN ./mvnw -pl orchestrator package -Pnative
# Stage 2 : Build a native image
FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=build /code/orchestrator/target/*-runner /application
EXPOSE 8080
USER root
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
