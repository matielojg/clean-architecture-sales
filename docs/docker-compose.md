# 🐳 Execução com Docker Compose

Este documento detalha a execução dos módulos usando Docker Compose.

## Estrutura

- `docker-compose.yml`: Contém todos os serviços necessários:
  - Infraestrutura: PostgreSQL, mock da distribuidora, SonarQube
  - Aplicações: salesorder-api-rest, resale-api-rest

---

## Como executar

### Subir todos os serviços

```bash
docker compose up -d
```

### Reconstruir as imagens (após alterações no código)

```bash
docker compose up -d --build
```

### Derrubar todos os containers e volumes

```bash
docker compose down -v
```

### Verificar logs

```bash
docker compose logs -f salesorder-api
docker compose logs -f resale-api
```
