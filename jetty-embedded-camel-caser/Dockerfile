FROM openjdk:11
ARG JAR_FILE=target/*with-dependencies.jar
ARG JKS FILE=camelCaser.jks
COPY ${JAR_FILE} app.jar
COPY ${JKS_FILE} ${JKS_FILE}
ENTRYPOINT ["java","-jar","/app.jar"]
