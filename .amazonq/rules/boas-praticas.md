# Regras e Boas Práticas para o Monorepo Clean Architecture

Este arquivo define regras e boas práticas para o Amazon Q ao trabalhar com este monorepo modularizado seguindo Clean Architecture.

## Estrutura e Modularidade

- Respeite a separação de camadas da Clean Architecture: domain → usecase → gateway → adapters
- Nunca crie dependências cíclicas entre módulos
- Módulos de domínio não devem depender de frameworks externos
- Módulos de caso de uso só podem depender do domínio
- Gateways definem contratos, não implementações
- Adapters implementam gateways e podem depender de frameworks

## Padrões de Código

- Entidades de domínio devem ser imutáveis e encapsular regras de negócio
- Value Objects (VOs) devem validar-se na construção
- Use exceções de domínio para erros de regras de negócio
- Casos de uso devem ter uma única responsabilidade
- Implemente interfaces para todos os gateways
- Adapters devem traduzir entre o mundo externo e o domínio

## Testes

- Testes unitários para domínio e casos de uso (sem mocks de frameworks)
- Testes de integração para adapters (com mocks de dependências externas)
- Testes end-to-end para APIs REST
- Use Test Doubles apropriados: Stubs para queries, Mocks para commands
- Teste exceções e casos de borda explicitamente

## Documentação

- Documente a intenção de cada módulo em seu README.md
- Use Javadoc para interfaces públicas e classes de domínio
- Mantenha diagramas atualizados para fluxos complexos
- Documente decisões arquiteturais em ADRs (Architecture Decision Records)

## Versionamento

- Mantenha compatibilidade entre módulos relacionados
- Versione APIs públicas seguindo Semantic Versioning
- Documente breaking changes em CHANGELOG.md

## Dependências

- Minimize dependências externas nos módulos core
- Centralize versões de dependências no build.gradle.kts raiz
- Evite duplicação de dependências entre módulos
- Use o mínimo de dependências necessárias em cada módulo

## Contextos Específicos

### Contexto de Revendedor (Resale)

- Entidades principais: Reseller, Address
- Validações críticas: CNPJ, Email, Endereço
- Regras de negócio: Aprovação, Limites de crédito

### Contexto de Pedidos (SalesOrder)

- Entidades principais: SalesOrder, SalesOrderItem
- Validações críticas: SKU, Quantidade, Preço
- Regras de negócio: Processamento, Distribuição, Status

## Convenções de Nomenclatura

- Módulos: `<contexto>-<camada>-<detalhe>`
- Pacotes: `com.github.matielojg.<contexto>.core.<camada>`
- Classes de domínio: substantivos sem sufixos
- Interfaces de caso de uso: verbos no infinitivo
- Implementações: sufixo `Impl`
- Gateways: sufixo `Gateway` ou `Repository`
- Adapters: prefixo com tecnologia (ex: `Jpa`, `Rest`)