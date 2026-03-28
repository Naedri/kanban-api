# ---------- BUILD STAGE ----------
FROM maven:3.9.14-ibm-semeru-21-noble AS builder
WORKDIR /build

# Copy only dependencies to allow caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build code and package it in distributable format
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM ibm-semeru-runtimes:open-21-jre-noble as application
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]