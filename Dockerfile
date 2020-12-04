FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecommercewebsite-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ecommercewebsite-0.0.1-SNAPSHOT.jar"]