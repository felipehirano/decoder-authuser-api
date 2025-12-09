# First stage: Create a stage for downloading dependencies.
# This stage uses a Maven wrapper to download all dependencies defined in the
# project's pom.xml file. The dependencies are cached in a Docker layer to speed
# up future builds.

FROM eclipse-temurin:21-jdk-jammy as deps

WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests

################################################################################

# Second stage: Create a stage for packaging the application.
# This stage copies the source code into the image and uses the cached
# dependencies from the previous stage to build and package the application.

FROM deps as package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

################################################################################

# Third stage: Create a stage for extracting the application layers.
# This stage uses Spring Boot's layer tools to extract the packaged application
# into separate layers. This allows for more efficient Docker images by separating
# frequently changing layers (like application code) from less frequently changing
# layers (like dependencies).

FROM package as extract

WORKDIR /build

RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

################################################################################

# Final stage: Create the final runtime image.
# This stage uses a lightweight JRE base image and copies the extracted layers
# from the previous stage into the final image. It also sets up a non-privileged
# user to run the application for better security.

FROM eclipse-temurin:21-jre-jammy AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

EXPOSE 8087

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]