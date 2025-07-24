FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiar arquivos de configuração do Gradle
COPY gradle gradle
COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .

# Copiar módulos de domínio e dependências
COPY salesorder-core-domain salesorder-core-domain
COPY salesorder-core-gateway salesorder-core-gateway
COPY salesorder-core-usecase salesorder-core-usecase
COPY salesorder-adapter-repository-jpa salesorder-adapter-repository-jpa
COPY salesorder-adapter-gateway-distributor salesorder-adapter-gateway-distributor
COPY salesorder-adapter-gateway-logger salesorder-adapter-gateway-logger
COPY salesorder-api-rest salesorder-api-rest

# Dar permissão de execução ao gradlew
RUN chmod +x ./gradlew

# Compilar o projeto
RUN ./gradlew :salesorder-api-rest:bootJar -PenableBootJar=true --no-daemon

# Verificar se o JAR foi criado
RUN ls -la salesorder-api-rest/build/libs/

# Imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar o JAR compilado
COPY --from=build /app/salesorder-api-rest/build/libs/salesorder-api-rest-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]