FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar app.jar

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]