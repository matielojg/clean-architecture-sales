# ![Beer mug representing the Sales Order module](salesorder-api-rest/src/main/resources/static/favicon.ico) Revenda Pedidos – Monorepo 

## [Evidências dos entregáveis](docs/EVIDENCES.md)

Este repositório contém a base do sistema de revenda e processamento de pedidos. Está organizado como **monorepo modularizado**, mas com estrutura pronta para futura **extração de microserviços independentes** (`resale`, `salesorder`, etc).

## 🧱 Estrutura do projeto

```text
revenda-pedidos-api/
├── resale-api-rest/                  # API HTTP do contexto de revendedores
├── resale-core-domain/              # Domínio puro (entidades, VOs, regras)
├── resale-core-usecase/             # Casos de uso (aplicação)
├── resale-core-gateway/             # Contratos com mundo externo
├── resale-adapter-repository-jpa/   # Persistência JPA
├── resale-adapter-gateway-validator/# Ex: validação externa de CNPJ/email

├── salesorder-api-rest/             # API HTTP do contexto de pedidos
├── salesorder-core-domain/          # Domínio puro
├── salesorder-core-usecase/         # Casos de uso
├── salesorder-core-gateway/         # Contratos de saída
├── salesorder-adapter-repository-jpa/
├── salesorder-adapter-gateway-distributor/
├── salesorder-adapter-gateway-logger/

├── distributor-mock/                # Mock HTTP da distribuidora (em Docker)
├── build-and-run.sh                 # Script de build + boot
├── test-salesorder.sh               # Testes manuais via HTTPie
└── docker-compose.yml
```

---

## 🧠 Arquitetura

- **Clean Architecture** (hexagonal) por módulo
- Cada contexto é isolado (`resale`, `salesorder`)
- Separação clara entre domínio, casos de uso, gateways e adapters
- Sem dependência de frameworks no domínio/usecase
- Totalmente testável e extensível

---

## 🐳 Execução com Docker Compose (recomendada)

Para executar todo o projeto (incluindo bancos de dados, serviços e mocks), basta usar:

```bash
docker compose up -d
```

Isso irá:
1. Compilar e iniciar os serviços Spring Boot (salesorder-api-rest e resale-api-rest)
2. Iniciar o banco de dados PostgreSQL
3. Iniciar o mock do distribuidor
4. Iniciar o SonarQube para análise de código

Para mais detalhes sobre como usar \`docker-compose.yml\` + \`docker-compose.override.yml\`, acesse:  
[docs/docker-compose.md](./docs/docker-compose.md)

---

## 🧪 Testes manuais com HTTPie

```bash
./test-salesorder.sh
```

> Interativo, testa pedidos válidos e inválidos na API \`/api/sales-orders\`.

---

## 🧪 Testes automatizados

```bash
./gradlew clean test
```

- ✅ Testes unitários: domínio, usecases
- ✅ Testes de integração: controller, repository, gateway
- ✅ Testes HTTP integrados com `RestTemplate` e `MockDistributorController`

---

## 🧰 Perfis disponíveis

| Perfil     | Descrição                                   |
|------------|---------------------------------------------|
| \`dev\`    | H2 em memória, mock desativado              |
| \`test\`   | Para testes automatizados (JUnit)           |
| \`docker\` | Usa Postgres e distribuidor-mock via Docker |

---

## 📈 Qualidade de código

SonarQube disponível em: [http://localhost:9000](http://localhost:9000)  
Login: \`admin\` / \`admin\`

---

## 📊 Observabilidade com DataDog

Este projeto está configurado para enviar métricas, traces e logs para o DataDog.

### Configuração

1. Crie uma conta no [DataDog](https://www.datadoghq.com/)
2. Obtenha sua chave de API
3. Crie um arquivo `.env` na raiz do projeto com o conteúdo:
   ```
   DD_API_KEY=your_datadog_api_key_here
   ```
4. Execute o projeto com Docker Compose:
   ```bash
   docker compose up -d
   ```

### Dashboards

Acesse o DataDog para visualizar:
- Métricas de performance da aplicação
- Traces de requisições
- Logs consolidados
- Alertas configurados


### 🚧 Em andamento

- [ ] Eventual consistency via eventos de domínio
- [ ] Validação por VO usando adapter externo (ex: SKU válido)

---


---

## 🌐 Extensões planejadas

- [ ] Fila/eventos para processamentos assíncronos
- [ ] Validações externas de CNPJ e Email
- [ ] Painel admin para status de pedidos

---

## 🚧 Em construção

Este repositório segue em evolução contínua, e já está estruturado para:

- extração futura de microserviços por contexto
- testes isolados por módulo
- versionamento e deploy separados

---

