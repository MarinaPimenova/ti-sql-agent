FROM alpine/java:25-jre

LABEL org.opencontainers.image.source=https://github.com/MarinaPimenova/ti-sql-agent

COPY build/libs/*.jar /app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/app.jar"]
