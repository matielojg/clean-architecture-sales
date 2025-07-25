#!/bin/bash
set -e

echo ""
echo "🚀 Iniciando processo de build e validação das APIs Spring Boot..."

# Etapa 1: Build Gradle
echo ""
echo "🔧 Etapa 1: Build do projeto (./gradlew clean build)"
./gradlew clean build --warning-mode=all

echo ""
echo "✅ Build concluído com sucesso."

# Etapa 2: Rebuild das imagens Docker
echo ""
echo "🐳 Etapa 2: Rebuild forçado das imagens Docker (sem cache)"
docker compose build --no-cache salesorder-api resale-api

# Etapa 3: Subir containers
echo ""
echo "📦 Etapa 3: Subindo containers Docker..."
docker compose up -d salesorder-api resale-api

# Etapa 4: Verificar inicialização
echo ""
echo "⏳ Etapa 4: Aguardando inicialização das aplicações..."
./scripts/wait-for-apps.sh

# Etapa 5: Resultado final
echo ""
if [ $? -eq 0 ]; then
  echo "✅ APIs Spring Boot estão rodando com sucesso!"
  echo "📊 Endpoints disponíveis:"
  echo "   • SalesOrder API: http://localhost:8080/api/sales-orders"
  echo "   • Resale API:     http://localhost:8081/resellers"
  echo "   • Health Check:   http://localhost:8080/actuator/health"
  echo ""
  echo "🧪 Para executar testes de carga:"
  echo "   ./scripts/test-stress.sh"
  echo "   ./scripts/test-heavy-load.sh"
else
  echo "❌ Erro ao iniciar aplicações. Exibindo logs recentes:"
  echo ""
  echo "🔍 Log: salesorder-api-rest"
  docker logs --tail 30 salesorder-api-rest
  echo ""
  echo "🔍 Log: resale-api-rest"
  docker logs --tail 30 resale-api-rest
  exit 1
fi
