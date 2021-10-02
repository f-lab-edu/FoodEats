FROM openjdk:11-jre-slim
ARG JAR_FILE=./build/libs/FoodEats-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} foodeats.jar
ENTRYPOINT ["java","-jar","/foodeats.jar"]
