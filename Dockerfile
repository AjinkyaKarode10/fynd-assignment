FROM openjdk:8
ADD target/batch-processing.jar batch-processing.jar
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "batch-processing.jar"]