#!/bin/bash

echo "🔄 Parando todos os daemons do Gradle..."
./gradlew --stop

echo "🔄 Limpando cache local do projeto..."
rm -rf .gradle

echo "🛠️ Executando clean build..."
./gradlew clean build --build-cache

if [ $? -ne 0 ]; then
    echo "❌ Build falhou. Verifique o erro acima."
    exit 1
fi

echo "🚀 Iniciando aplicação (api-rest)..."
./gradlew :api-rest:bootRun
