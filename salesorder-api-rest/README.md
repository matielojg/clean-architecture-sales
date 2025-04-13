---

# 🧩 Revenda Pedidos – Monorepo

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

## 🐳 Como executar tudo com Docker

```bash
./build-and-run.sh
```

> Sobe os containers (PostgreSQL + distribuidor mock) e inicia a aplicação `salesorder-api-rest`.

---

## 🧪 Testes manuais com HTTPie

```bash
./test-salesorder.sh
```

> Interativo, testa pedidos válidos e inválidos na API `/api/sales-orders`.

---

## 🧪 Testes automatizados

```bash
./gradlew clean test
```

- Testes unitários: domínio, usecases
- Testes de integração: controller, repository, gateway

---

## 🧰 Perfis disponíveis

| Perfil  | Descrição                               |
|---------|------------------------------------------|
| `dev`   | H2 em memória, mock desativado           |
| `test`  | Para testes automatizados (JUnit)        |
| `docker`| Usa Postgres e distribuidor-mock via Docker |

---

## 📈 Qualidade de código

SonarQube disponível em: [http://localhost:9000](http://localhost:9000)  
Login: `admin` / `admin`

---

## 🌐 Extensões planejadas

- [ ] Documentação com Swagger (OpenAPI)
- [ ] Carga inicial de dados (`CommandLineRunner`)
- [ ] Resiliência (retry, fallback, reprocessamento)
- [ ] Emissão de eventos para mensageria

---

## 🚧 Em construção

Este repositório segue em evolução contínua, e já está estruturado para:

- extração futura de microserviços por contexto
- testes isolados por módulo
- versionamento e deploy separados

---

Se quiser o mesmo modelo para os `README.md` dos módulos internos, posso gerar já com tabela de endpoints, perfis e comandos úteis. Quer seguir com o do `salesorder-api-rest` em seguida?