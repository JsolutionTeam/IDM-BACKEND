# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim
# slim 버전
# FROM openjdk:17-jdk-slim

# Add Author info
LABEL maintainer="j-sol.co.kr"

# Make port 15006 available to the world outside this container
EXPOSE 11402

# The application's jar file
#ARG JAR_FILE=./api/build/libs/*-SNAPSHOT.jar
#
## Add the application's jar to the container
#ADD ${JAR_FILE} idm-prod-server.jar

COPY ./api/build/libs/*-SNAPSHOT.jar ./app.jar

#ENTRYPOINT ["java", "-jar", "/app.jar"]

# Run the jar file, /idm-prod-server.jar 에서 제일 앞의 '/' 는 필수임
ENTRYPOINT ["java","-jar","/app.jar", "-Duser.timezone=Asia/Seoul"]
# Example
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-do-springboot.jar"]
# => java -Djava.security.edg=file:/dev/./urandom -jar /to-do-springboot.jar
