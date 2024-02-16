FROM openjdk:17-jdk-slim

# JAR_FILE 변수 정의, plain과 구분하기 위해 명시했음.
ARG JAR_FILE=api/build/libs/*.jar

# JAR 파일 메인 디렉토리에 복사
COPY ${JAR_FILE} idm_prod_server.jar

# 시스템 진입점 정의
ENTRYPOINT ["java","-jar","/idm_prod_server.jar", "--spring.profiles.active=prod"]
