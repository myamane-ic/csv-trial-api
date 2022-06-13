FROM openjdk:17-jdk-alpine

EXPOSE 8080

ENV TZ Asia/Tokyo
ENV JAR_FILE = trial.jar

COPY build/libs/trial-0.0.1-SNAPSHOT.jar $JAR_FILE
RUN chmod 755 $JAR_JAR_FILE

ENTRYPOINT ["java","-jar",$JAR_FILE]