FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

WORKDIR /app

COPY . .

WORKDIR /app/modules/load-balancer

RUN sbt clean assembly

EXPOSE 8080

CMD ["java", "-jar", "target/scala-3.3.5/load-balancer-assembly-0.1.0-SNAPSHOT.jar"]
