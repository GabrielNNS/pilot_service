# PILOT SERVICE - API

Projeto desenvolvido em Java utilizando Spring Boot com foco no gerenciamento de Pilotos.

---

## 1. Requisitos

Necessário possuir:

- Docker Desktop (Rodando antes da execução)  
  Download: https://www.docker.com/products/docker-desktop/

Para subir a aplicação deve executar o script auxiliar:

- Windows -> `.\start-app.ps1`
- Linux -> Em andamento
- MacOS -> Em andamento

**OBS** – Toda configuração necessária será realizada pelo script, basta executá-lo.

**OBS 2** – A versão do Java usada no projeto será baixada durante a execução caso não haja instalação na máquina local.

---

## 2. Sobre o projeto

O **Pilot Service** é uma API REST responsável pelo gerenciamento de pilotos.

A aplicação segue:

- Arquitetura modular por domínio.
- Estrutura em camadas (Controller, Service, Domain, Repository).
- Separação clara entre DTOs e Entidades.
- Padrão RESTful para exposição dos endpoints.

O módulo de Pilot é responsável por:

- Cadastro de pilotos
- Consulta por ID
- Listagem geral
- Atualização de dados
- Remoção de registros

---

## Endpoints

### Pilotos (`/api/v1/pilots`)

**POST /api/v1/pilots**  
Cria um novo piloto no sistema.

**GET /api/v1/pilots/{id}**  
Busca os detalhes de um piloto específico através do seu ID.

**GET /api/v1/pilots**  
Lista todos os pilotos cadastrados na base de dados.

**PUT /api/v1/pilots/{id}**  
Atualiza os dados de um piloto existente com base no ID fornecido.

**DELETE /api/v1/pilots/{id}**  
Remove um piloto do sistema de forma definitiva.

---

## Decisões

- A API foi construída com foco em clareza arquitetural e boas práticas.
- Separação entre objetos de entrada (Request), saída (Response) e atualização (UpdateRequest).
- Uso de DTOs para evitar exposição direta das entidades.
- Estrutura preparada para evolução futura (relacionamentos, autenticação mais robusta, etc).

---

## Tecnologias e Dependências

O projeto utiliza o **Gradle (Kotlin DSL)** como gerenciador de dependências:

### Framework Core
- Spring Boot 3
- spring-boot-starter-web (Web MVC)

### Persistência e Banco de Dados
- spring-boot-starter-data-jpa (Hibernate)
- PostgreSQL como banco principal
- H2 como banco em memória para desenvolvimento/testes
- Flyway para versionamento de banco

### Segurança
- spring-security para autenticação básica

### Mapeamento e Produtividade
- MapStruct para conversão automática entre Entity e DTO
- Lombok para redução de código boilerplate

### Documentação e Validação
- springdoc-openapi (Swagger UI) para documentação interativa
- spring-boot-starter-validation para validação de dados de entrada

### Testes
- spring-boot-starter-test (JUnit 5 e Mockito)
- spring-security-test para testes em rotas protegidas

---

## Importante

Esse projeto tem fins de estudo e aprendizado.

O banco roda localmente via Docker e todas as variáveis de ambiente dentro do arquivo `start-app` são fictícias.