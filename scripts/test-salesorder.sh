#!/bin/bash
set -e

# 🧠 Função de escolha do ambiente
function escolher_url() {
  echo "🔍 Onde está rodando a aplicação sales-order?"
  echo "1) Localhost (porta padrão 8080)"
  echo "2) Docker (porta mapeada: 8088)"
  echo "3) Inserir porta manualmente (ex: 8085)"
  read -p "👉 Escolha uma opção [1-3]: " opcao

  case $opcao in
    1) BASE_URL="http://localhost:8080" ;;
    2) BASE_URL="http://localhost:8088" ;;
    3)
      read -p "🔢 Digite a porta: " porta
      BASE_URL="http://localhost:$porta"
      ;;
    *)
      echo "❌ Opção inválida. Encerrando."
      exit 1
      ;;
  esac
}

# 🚦 Verifica se httpie está instalado
if ! command -v http &> /dev/null; then
  echo "🚫 O 'httpie' (comando 'http') não está instalado."
  read -p "❓ Deseja instalar agora via apt? [s/N]: " choice
  if [[ "$choice" =~ ^[sS]$ ]]; then
    sudo apt update && sudo apt install -y httpie
  else
    echo "❌ Instalação abortada. Não é possível continuar sem httpie."
    exit 1
  fi
fi

# 🔁 Chamar função de escolha
escolher_url
echo "🌐 Testando contra: $BASE_URL"

echo "📦 Testando pedido de revenda (válido)..."
http --check-status --print=hb \
  POST $BASE_URL/api/sales-orders \
  Content-Type:application/json \
  resellerId=3fa85f64-5717-4562-b3fc-2c963f66afa6 \
  items:='[
    {"skuCode":"SKU-123","quantity":600},
    {"skuCode":"SKU-456","quantity":500}
  ]'

echo -e "\n✅ Pedido válido enviado com sucesso."

echo -e "\n📦 Testando pedido inválido (quantidade < 1000)..."
if http --check-status --print=hb \
  POST $BASE_URL/api/sales-orders \
  Content-Type:application/json \
  resellerId=3fa85f64-5717-4562-b3fc-2c963f66afa6 \
  items:='[
    {"skuCode":"SKU-000","quantity":200}
  ]'; then
  echo "❌ ERRO: Pedido inválido foi aceito, mas deveria falhar."
  exit 1
else
  echo "✅ Pedido inválido corretamente rejeitado."
fi
