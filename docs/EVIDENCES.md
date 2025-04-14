
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

- [x] Carga inicial com `CommandLineRunner` \
  ↳ [`SalesDataLoader.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/salesorder-api-rest/src/main/java/com/github/matielojg/salesorder/api/config/SalesDataLoader.java)

- [x] Carga inicial com `CommandLineRunner` \
  ↳ [`ResellerDataLoader.java`](https://github.com/matielojg/clean-architecture-sales/blob/main/resale-api-rest/src/main/java/com/github/matielojg/revenda/api/config/ResellerDataLoader.java)

  

---