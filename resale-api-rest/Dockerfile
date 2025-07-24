FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiar arquivos de configuração do Gradle
COPY gradle gradle
COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .

# Copiar módulos de domínio e dependências
COPY resale-core-domain resale-core-domain
COPY resale-core-gateway resale-core-gateway
COPY resale-core-usecase resale-core-usecase
COPY resale-adapter-repository-jpa resale-adapter-repository-jpa
COPY resale-adapter-gateway-validator resale-adapter-gateway-validator
COPY resale-adapter-gateway-api resale-adapter-gateway-api
COPY resale-api-rest resale-api-rest

# Dar permissão de execução ao gradlew
RUN chmod +x ./gradlew

# Compilar o projeto
RUN ./gradlew :resale-api-rest:bootJar -PenableBootJar=true --no-daemon

# Verificar se o JAR foi criado
RUN ls -la resale-api-rest/build/libs/

# Imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar o JAR compilado
COPY --from=build /app/resale-api-rest/build/libs/resale-api-rest-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]