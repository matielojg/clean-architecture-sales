# 🐳 Execução com Docker Compose

Este documento detalha a execução dos módulos usando Docker Compose com múltiplos arquivos.

## Estrutura

- `docker-compose.yml`: serviços de infraestrutura (PostgreSQL, mock da distribuidora)
- `docker-compose.override.yml`: aplicações Spring Boot (`salesorder-api`, `resale-api`)

---

## Como executar

### Subir infraestrutura

```bash
docker compose up -d
```
### Subir tudo (infra + apps)

```bash
docker compose -f docker-compose.yml -f docker-compose.override.yml up -d --build
```
### Derrubar todos os containers e volumes

```bash
docker compose down -v
```
