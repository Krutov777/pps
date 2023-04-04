FROM openjdk:17-jdk-alpine
COPY pps-0.0.1-SNAPSHOT.jar springbootify.jar
CMD ["java","-jar","springbootify.jar"]