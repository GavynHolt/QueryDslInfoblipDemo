FROM azul/zulu-openjdk:25-jre

WORKDIR /app

COPY build/libs/query-dsl-infoblip-demo-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]