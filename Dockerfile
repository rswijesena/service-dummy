FROM java:8

EXPOSE 8080

CMD ["java", "-jar", "*-jar-with-dependencies.jar"

WORKDIR /app

ADD . ./app