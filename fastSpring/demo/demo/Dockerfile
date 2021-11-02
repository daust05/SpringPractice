FROM java:8

LABEL maintainer="daust05@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} jpa-game.jar

ENTRYPOINT ["java", "-jar", "/jpa-game.jar"]