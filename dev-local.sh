#!/bin/bash

echo "🚀 Iniciando ambiente de desenvolvimento local"
echo "📝 Este script inicia os serviços Spring Boot localmente para desenvolvimento"
echo "⚠️  Certifique-se de que a infraestrutura está rodando com 'docker compose up -d'"
echo ""

# Start resale-api-rest em background
./gradlew :resale-api-rest:bootRun -Dspring.profiles.active=dev &
PID_RESALE=$!

# Start salesorder-api-rest em background com resources corretos
./gradlew :salesorder-api-rest:bootRun -Dspring.profiles.active=dev \
    -Dspring.config.location=classpath:/application.yml &
PID_SALESORDER=$!

# Espera CTRL+C e mata os processos
trap "echo '🛑 Encerrando apps...'; kill $PID_RESALE $PID_SALESORDER" SIGINT

echo "✅ Serviços iniciados:"
echo "   - resale-api-rest: http://localhost:8081"
echo "   - salesorder-api-rest: http://localhost:8080"
echo ""
echo "🔄 Pressione Ctrl+C para encerrar todos os serviços"

wait