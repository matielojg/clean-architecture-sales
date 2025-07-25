#!/bin/bash
echo "⚡ Teste de carga pesada com Apache Bench..."

# Verificar se ab está instalado
if ! command -v ab &> /dev/null; then
    echo "❌ Apache Bench não encontrado. Instale com:"
    echo "   sudo apt install apache2-utils"
    exit 1
fi

# Criar diretório para resultados
mkdir -p results/load-tests

echo "🎯 Testando GET /api/sales-orders"
ab -n 1000 -c 10 -g results/load-tests/get-sales-orders.tsv "http://localhost:8080/api/sales-orders?status=PROCESSING"

echo ""
echo "🎯 Testando POST /resellers (único endpoint disponível)"
echo "📦 Enviando 500 requisições simultâneas para registro de revendedores..."

# Payload padrão para criação de revendedor
JSON_PAYLOAD='{"name": "Carga Pesada", "cnpj": "11111111000191", "email": "carga+$(date +%s)@teste.com"}'

# Loop para gerar 500 POSTs com curl (simulação paralela com xargs)
seq 1 500 | xargs -P10 -I{} curl -s -X POST http://localhost:8081/resellers \
  -H "Content-Type: application/json" \
  -d "$JSON_PAYLOAD" > /dev/null

echo ""
echo "✅ Teste de carga concluído!"
echo "📊 Resultados do GET salvos em: results/load-tests/get-sales-orders.tsv"
echo "📨 POSTs enviados para /resellers (verifique métricas com reseller.register no DataDog)"
