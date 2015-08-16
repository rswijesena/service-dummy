FROM java:8

EXPOSE 8080

WORKDIR /app

ADD ./app

CMD ["java", "-jar", "*-jar-with-dependencies.jar"