# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim
# slim 버전
# FROM openjdk:17-jdk-slim

# Add Author info
LABEL maintainer="j-sol.co.kr"

# Make port 15006 available to the world outside this container
EXPOSE 11402

# The application's jar file
ARG JAR_FILE=api/build/libs/api.jar

# Add the application's jar to the container
ADD ${JAR_FILE} idm-prod-server.jar

# Run the jar file
ENTRYPOINT ["java","-jar","idm-prod-server.jar", "-Duser.timezone=Asia/Seoul"]
# Example
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-do-springboot.jar"]
# => java -Djava.security.edg=file:/dev/./urandom -jar /to-do-springboot.jar
