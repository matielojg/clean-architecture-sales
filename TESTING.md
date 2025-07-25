# Roteiro para Testar a Aplicação

Este documento fornece instruções passo a passo para testar a aplicação Clean Architecture Sales.

## 1. Preparação do Ambiente

```bash
# Clone o repositório (se ainda não tiver feito)
git clone <url-do-repositorio>
cd clean-architecture-sales

# Inicie a infraestrutura com Docker Compose
docker compose up -d
```

## 2. Compilação e Execução

```bash
# Compile e execute a aplicação com Docker Compose
docker compose up -d

# Alternativamente, para desenvolvimento local:
./gradlew :salesorder-api-rest:bootRun -Dspring.profiles.active=dev
./gradlew :resale-api-rest:bootRun -Dspring.profiles.active=dev
```

## 3. Testes Automatizados

```bash
# Execute todos os testes
./gradlew clean test

# Execute testes de um módulo específico
./gradlew :salesorder-core-domain:test
./gradlew :resale-api-rest:test
```

## 4. Testes Manuais com HTTPie

```bash
# Use o script de teste fornecido
./test-salesorder.sh

# Ou teste manualmente com HTTPie:

# Criar um revendedor
http POST :8080/resellers \
  corporateName="Distribuidora ABC" \
  email="contato@distribuidoraabc.com.br" \
  cnpj="12345678000199"

# Criar um pedido de venda
http POST :8080/api/sales-orders \
  resellerId=<id-do-revendedor> \
  items:='[{"skuCode": "SKU-001", "quantity": 100}, {"skuCode": "SKU-002", "quantity": 50}]'

# Listar pedidos por status
http GET ':8080/api/sales-orders?status=PENDING'
http GET ':8080/api/sales-orders?status=SENT'
```

## 5. Verificação de Logs

```bash
# Verificar logs da aplicação
docker logs -f salesorder-api-rest
docker logs -f resale-api-rest

# Verificar logs do distribuidor mock
docker logs -f distributor-mock
```

## 6. Monitoramento com SonarQube

```bash
# Acesse o SonarQube para verificar a qualidade do código
# URL: http://localhost:9000
# Login: admin / admin

# Execute a análise do SonarQube (se tiver o token configurado)
export SONAR_TOKEN=<seu-token>
./gradlew sonarqube
```

## 7. Testes de Integração Específicos

```bash
# Teste de integração do controller de pedidos
./gradlew :salesorder-api-rest:test --tests "com.github.matielojg.salesorder.api.controller.SalesOrderControllerIT"

# Teste de integração do controller de revendedores
./gradlew :resale-api-rest:test --tests "com.github.matielojg.revenda.api.controller.ResellerControllerIT"
```

## 8. Verificação de Endpoints com Swagger

Acesse a documentação Swagger para testar os endpoints via interface gráfica:
- Pedidos: http://localhost:8080/swagger-ui/index.html
- Revendedores: http://localhost:8081/swagger-ui/index.html

## 9. Testes de Carga (Opcional)

```bash
# Instale o k6 se necessário
# Execute um teste de carga básico
k6 run -u 10 -d 30s tests/load/sales-order-api.js
```

## 10. Limpeza do Ambiente

```bash
# Pare os containers
docker compose down

# Limpe os dados (se necessário)
docker compose down -v
```