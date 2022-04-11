# Stage 1 : Build a native artifact
FROM quay.io/quarkus/ubi-quarkus-native-image:21.2.0-java11 AS build
COPY . /code/
USER root
WORKDIR /code
RUN ./mvnw -pl shared-resources install
RUN ./mvnw -pl batch-processor package -Pnative
# Stage 2 : Build a native image
FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=build /code/batch-processor/target/*-runner /application
EXPOSE 8080
USER root
ENTRYPOINT ["./application", "-Dquarkus.http.host=0.0.0.0"]
