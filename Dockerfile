FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/*.jar alchiveserver.jar

ENTRYPOINT ["java", "-jar", "/alchiveserver.jar"]