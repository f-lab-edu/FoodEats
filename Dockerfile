FROM openjdk:11-jdk
ARG JAR_FILE=./build/libs/FoodEats-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} foodeats.jar
ENTRYPOINT ["java","-jar","/foodeats.jar"]