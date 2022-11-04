FROM openjdk:18.0.2.1
COPY target/my-dictionary-0.0.1-SNAPSHOT.jar my-dictionary.jar
ENTRYPOINT ["java","-jar","/my-dictionary.jar"]