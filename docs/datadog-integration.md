# Integração com DataDog

Este documento descreve como o projeto Clean Architecture Sales está integrado com o DataDog para observabilidade.

## Visão Geral

O projeto utiliza o DataDog para:
- **APM (Application Performance Monitoring)**: Rastreamento automático de requisições
- **Métricas customizadas**: Tempo de resposta dos endpoints via `@Timed`
- **Métricas JVM**: CPU, memória, garbage collection automáticas
- **Logs estruturados**: Via Logback com correlação de traces

## Configuração

### Agente DataDog

O agente DataDog roda como contêiner no `docker-compose.yml`:
- **APM habilitado**: Coleta traces automaticamente
- **Métricas de infraestrutura**: CPU, memória, rede do host
- **Região configurada**: `us5.datadoghq.com`

### Aplicações Java

Ambos os serviços (`salesorder-api` e `resale-api`) incluem:
- **Agente Java DataDog**: Baixado automaticamente nos Dockerfiles
- **Micrometer**: Para métricas customizadas via `@Timed`
- **TimedConfig**: Habilita suporte ao `@Timed` nos controllers

## Variáveis de Ambiente Necessárias

No arquivo `.env` na raiz do projeto:
```env
DD_API_KEY=sua_chave_api_aqui
DD_SITE=us5.datadoghq.com
```

**Importante**: Use uma **API Key** (não Application Key) obtida em Organization Settings > API Keys.

## Métricas Customizadas Disponíveis

O projeto envia as seguintes métricas customizadas:
- `salesorder.create`: Tempo de resposta para criar pedidos
- `salesorder.list`: Tempo de resposta para listar pedidos
- `reseller.register`: Tempo de resposta para registrar revendedor

## Acessando o Painel do DataDog

1. Acesse [https://us5.datadoghq.com](https://us5.datadoghq.com) (sua região)
2. Faça login com suas credenciais
3. Recursos disponíveis:
   - **APM > Services**: Visualizar `salesorder-api` e `resale-api`
   - **Metrics > Explorer**: Pesquisar por `salesorder.*` e `reseller.*`
   - **Infrastructure**: Monitorar contêineres Docker
   - **Dashboards**: Criar painéis personalizados

## Configuração Inicial

### 1. Obter Chave de API

1. Acesse [https://us5.datadoghq.com/organization-settings/api-keys](https://us5.datadoghq.com/organization-settings/api-keys)
2. Clique em **+ New Key** (aba **API Keys**, não Application Keys)
3. Dê um nome: "Clean Architecture Sales"
4. Copie a chave gerada

### 2. Configurar Ambiente

1. Copie o arquivo de exemplo:
   ```bash
   cp .env.example .env
   ```

2. Edite o arquivo `.env` com sua chave:
   ```env
   DD_API_KEY=sua_chave_api_aqui
   DD_SITE=us5.datadoghq.com
   POSTGRES_PASSWORD=postgres123
   ```

2. Execute a aplicação:
   ```bash
   docker-compose up -d
   ```

### 3. Testar Integração

Faça algumas requisições para gerar dados:
```bash
# Criar pedido
curl -X POST http://localhost:8080/api/sales-orders \
  -H "Content-Type: application/json" \
  -d '{"resellerId": 1, "items": [{"skuCode": "SKU001", "quantity": 10}]}'

# Registrar revendedor
curl -X POST http://localhost:8081/resellers \
  -H "Content-Type: application/json" \
  -d '{"corporateName": "Test Corp", "email": "test@test.com", "cnpj": "12345678000195"}'
```

## Solução de Problemas

### Erro "API Key invalid"

**Causa**: Chave incorreta ou região errada

**Solução**:
1. Verifique se está usando **API Key** (não Application Key)
2. Confirme a região: `DD_SITE=us5.datadoghq.com`
3. Use o script para atualizar:
   ```bash
   bash update-datadog-key.sh NOVA_CHAVE_API
   ```

### Verificar Status

```bash
# Status do contêiner
docker ps | grep datadog-agent

# Logs do agente
docker logs datadog-agent

# Variáveis de ambiente
docker exec datadog-agent env | grep DD_
```

### Dados não aparecem

1. **Aguarde 2-3 minutos** para sincronização inicial
2. **Faça requisições** para gerar traces e métricas
3. **Verifique a região**: Acesse `us5.datadoghq.com` (não `app.datadoghq.com`)