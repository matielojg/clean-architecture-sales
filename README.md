# 🧩 Revenda Pedidos – Monorepo

![🍺Beer mug representing the Sales Order module](salesorder-api-rest/src/main/resources/static/favicon.ico)

Este repositório contém a base do sistema de revenda e processamento de pedidos. Está organizado como **monorepo modularizado**, mas com estrutura pronta para futura **extração de microserviços independentes** (`resale`, `salesorder`, etc).

---

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

## 🐳 Como executar tudo com Docker + Gradle

Este comando compila as aplicações e inicia os dois serviços Spring Boot (salesorder e resale).  
⚠️ Antes de executá-lo, certifique-se de que a infraestrutura (banco e mock) já esteja rodando com Docker Compose.

```bash
docker compose up -d &&
./build-and-run.sh
```

---

## 🐳 Execução com Docker Compose (recomendada)

Para mais detalhes sobre como usar \`docker-compose.yml\` + \`docker-compose.override.yml\`, acesse:  
[docs/docker-compose.md](./docs/docker-compose.md)

> Sobe os containers (PostgreSQL + distribuidor mock) e inicia a aplicação \`salesorder-api-rest\`.

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

## 📌 Funcionalidades já entregues

[//]: # (✅ Registro de revendedores com validação  )

[//]: # (✅ Cadastro de pedidos com validação e regras  )

[//]: # (✅ Persistência JPA para revendedores e pedidos  )

[//]: # (✅ Retry + fallback no envio de pedidos  )

[//]: # (✅ Reprocessamento de pedidos com falha  )

[//]: # (✅ Mock de distribuidor via Docker  )

[//]: # (✅ Documentação com Swagger &#40;OpenAPI&#41;  )

[//]: # (✅ Testes automatizados e manuais  )

[//]: # (✅ Separação de logs por camada &#40;LoggerGateway&#41;  )

[//]: # (✅ Geração de massa de dados com \`CommandLineRunner\`)


## ✅ Evidência de Implementação

Este checklist mostra os requisitos atendidos no módulo **salesorder** com links diretos para o código-fonte no GitHub.

> 🔗 Repositório: [matielojg/clean-architecture-sales](https://github.com/matielojg/clean-architecture-sales)

### 📦 Domínio e Casos de Uso

- [x] Entidade `SalesOrder` com validação de regras \
  ↳ [`SalesOrder.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-core-domain/src/main/java/com/github/matielojg/salesorder/core/domain/entity/SalesOrder.java)

- [x] Caso de uso `CreateSalesOrder` com regras \
  ↳ [`CreateSalesOrderImpl.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-core-usecase/src/main/java/com/github/matielojg/salesorder/core/usecase/impl/CreateSalesOrderImpl.java)

- [x] Retry + Fallback no envio para distribuidora \
  ↳ [`DistributorHttpClient.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-adapter-gateway-distributor/src/main/java/com/github/matielojg/salesorder/adapter/gateway/DistributorHttpClient.java)

- [x] Reprocessamento de pedidos com status `FAILED` \
  ↳ [`ReprocessFailedSalesOrdersImpl.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-core-usecase/src/main/java/com/github/matielojg/salesorder/core/usecase/impl/ReprocessFailedSalesOrdersImpl.java)

---

### 🧪 Testes Automatizados

- [x] Teste de integração do controller \
  ↳ [`SalesOrderControllerIT.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/test/java/com/github/matielojg/salesorder/api/controller/SalesOrderControllerIT.java)

- [x] Teste unitário com retry/fallback \
  ↳ [`DistributorHttpClientTest.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-adapter-gateway-distributor/src/test/java/com/github/matielojg/salesorder/adapter/gateway/DistributorHttpClientTest.java)

---

### 🔗 Endpoints das APIs

#### 🛒 Sales Order API
- **Swagger**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **H2 Console**: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)
  - **JDBC URL**: `jdbc:h2:mem:devdb`
  - **User Name**: `sa`
  - **Password**: `""`

#### 🔁 Resale API
- **Swagger**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **H2 Console**: [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)
  - **JDBC URL**: `jdbc:h2:mem:devdb`
  - **User Name**: `sa`
  - **Password**: `""`

---

### 📝 Observações
- As duas aplicações compartilham o mesmo nome de banco (`devdb`), mas são independentes em memória (`mem:`), pois cada app carrega seu próprio contexto.
- O console H2 pode ser acessado diretamente após a seção de Swagger para facilitar debugging sem precisar de ferramenta externa.
- A senha padrão para o H2, se aplicável, costuma ser vazia (`\"\"`), mas depende da configuração da aplicação.
---

### 🧩 API + Swagger

- [x] Controller anotado com Swagger \
  ↳ [`SalesOrderController.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/main/java/com/github/matielojg/salesorder/api/controller/SalesOrderController.java)

- [x] Documentação OpenAPI ativa no `localhost:8081/swagger-ui.html`

- [x] DTOs anotados com `@Schema` \
  ↳ [`SalesOrderRequest.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/main/java/com/github/matielojg/salesorder/api/controller/model/SalesOrderRequest.java)

---

### 🧪 Testes manuais via HTTPie

- [x] Script pronto para execução \
  ↳ [`test-salesorder.sh`](https://github.com/matielojg/clean-architecture-sales/blob/main/test-salesorder.sh)

---

### 🧃 Extras

- [x] Favicon 🧉 no módulo `salesorder` \
  ↳ [`favicon.ico`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/main/resources/static/favicon.ico)

- [x] Carga inicial com `CommandLineRunner` \
  ↳ [`SalesDataLoader.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/main/java/com/github/matielojg/salesorder/api/config/SalesDataLoader.java)

---

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

