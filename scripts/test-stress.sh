#!/bin/bash
echo "🚀 Iniciando teste de stress para validação DataDog..."

# Cores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para criar pedidos (POST)
create_orders() {
  echo -e "${BLUE}📦 Criando pedidos...${NC}"
  for i in {1..20}; do
    http --ignore-stdin POST :8080/api/sales-orders \
      resellerId="$(uuidgen)" \
      items:='[{"sku":"SKU-001","quantity":600},{"sku":"SKU-002","quantity":500}]' > /dev/null 2>&1 &
  done
  wait
  echo -e "${GREEN}✅ 20 pedidos criados${NC}"
}

# Função para consultar pedidos (GET)
query_orders() {
  echo -e "${BLUE}🔍 Consultando pedidos...${NC}"
  for i in {1..15}; do
    http --ignore-stdin GET :8080/api/sales-orders?status=PROCESSING > /dev/null 2>&1 &
  done
  wait
  echo -e "${GREEN}✅ 15 consultas realizadas${NC}"
}

# Função para criar revendedores
create_resellers() {
  echo -e "${BLUE}👥 Criando revendedores...${NC}"
  for i in {1..10}; do
    http --ignore-stdin POST :8081/resellers \
      cnpj="12.345.678/0001-9$i" \
      name="Loja Stress Test $i" \
      email="stress$i@test.com" > /dev/null 2>&1 &
  done
  wait
  echo -e "${GREEN}✅ 10 revendedores criados${NC}"
}

# Executar testes em ondas
echo "🌊 Onda 1: Criação de dados"
create_resellers
create_orders

echo ""
echo "🌊 Onda 2: Consultas intensivas"
query_orders

echo ""
echo "🌊 Onda 3: Carga mista"
create_orders &
query_orders &
wait

echo ""
echo -e "${GREEN}✅ Teste de stress concluído!${NC}"
echo ""
echo "📊 Verifique no DataDog:"
echo "   • APM Services: clean-architecture-sales"
echo "   • Traces: POST/GET /api/sales-orders"
echo "   • Logs: trace_id correlation"
echo "   • Metrics: @Timed annotations"