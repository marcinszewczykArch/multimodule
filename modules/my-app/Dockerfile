FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy CAŁY projekt do obrazu,
# bo kontekst build w docker-compose jest ustawiony na katalog główny `.`
COPY . .

# Przechodzimy do folderu modułu `my-app`
WORKDIR /app/modules/my-app

# Budujemy fat JAR (my-app-assembly-0.1.0.jar) przy pomocy sbt-assembly
RUN sbt clean assembly

# Aplikacja nasłuchuje na porcie 8080
EXPOSE 8080

# Uruchamiamy gotowy JAR
CMD ["java", "-jar", "target/scala-3.3.5/my-app-assembly-0.1.0-SNAPSHOT.jar"]
