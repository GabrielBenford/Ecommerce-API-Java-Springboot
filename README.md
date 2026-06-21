# 🛒 E-commerce API - Spring Boot

API RESTful completa para gerenciamento de e-commerce desenvolvida com Java e Spring Boot.

O projeto foi construído com foco em arquitetura limpa, segurança, boas práticas de desenvolvimento back-end e simulação de ambiente real de produção.

---

# 🚀 Tecnologias Utilizadas

## 🔹 Back-end
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication

## 🔹 Banco de Dados
- MySQL

## 🔹 DevOps
- Docker
- Docker Compose

## 🔹 Documentação
- Swagger / OpenAPI

## 🔹 Ferramentas
- Git
- GitHub
- Postman
- Maven

## 🔹 Testes
- JUnit
- Mockito
---

# 📌 Funcionalidades

✅ Cadastro de usuários  
✅ Login com autenticação JWT  
✅ Controle de autorização e segurança  
✅ CRUD completo de produtos  
✅ CRUD de pedidos  
✅ Controle de estoque  
✅ Tratamento global de exceptions
✅ Documentação completa da API
✅ Containerização com Docker 
✅ Testes unitários com Junit e Mockito
 

---

# 🧠 O Que Aprendi Neste Projeto

Durante o desenvolvimento deste projeto, aprofundei conhecimentos importantes para desenvolvimento back-end profissional:

## 🔥 Desenvolvimento Back-end
- Construção de APIs RESTful escaláveis
- Estruturação de camadas (Controller, Service, Repository)
- Boas práticas de organização de código
- DTOs e separação de responsabilidades

## 🔐 Segurança
- Implementação de autenticação JWT
- Controle de permissões com Spring Security
- Proteção de rotas privadas

## 🗄️ Banco de Dados
- Modelagem relacional
- Relacionamentos entre entidades
- Consultas com JPA/Hibernate

## 🐳 Docker
- Criação de containers
- Docker Compose para ambiente completo
- Padronização de ambiente de execução

## 🧪 Testes
- Testes unitários com JUnit
- Mockito para mocks e validação de comportamento
---

# 🏗️ Arquitetura do Projeto

```bash
EcommerceAPI
     src    
     ┣ main
        ┣java
     ┃    ┣ controllers
     ┃    ┣ services
     ┃    ┣ repositories
     ┃    ┣ entities
     ┃    ┣ dtos
     ┃    ┣ security
     ┃  ┃ ┣ exceptions
     ┃  ┃ ┗ config
     ┃  ┗ resources
     ┣ test
        ┗ java
             ┗serviceTest
       ┗ resources
```

---

# 🔐 Autenticação

O projeto utiliza autenticação baseada em JWT (JSON Web Token).

Fluxo:
1. Usuário realiza login
2. API gera token JWT
3. Token é enviado nas próximas requisições
4. Rotas privadas são protegidas via Spring Security

---

# 📖 Documentação Swagger

A documentação da API pode ser acessada em:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 🐳 Executando com Docker

## Clone o projeto

```bash
git clone https://github.com/GabrielBenford/ecommerce-api-java-springboot.git
```

## Execute os containers

```bash
docker-compose up --build
```

---

# ▶️ Executando Localmente

## Pré-requisitos
- Java 17+
- Maven
- Docker

## Rodando aplicação

```bash
mvn spring-boot:run
```
# 🧪 Executando Testes

```bash
./mvnw test
```

---
# 📬 Endpoints Principais

## 🔹 Auth
- POST /auth/login
- POST /auth/register

## 🔹 Produtos
- GET /products
- POST /products
- PUT /products/{id}
- DELETE /products/{id}

## 🔹 Pedidos
- GET /orders
- POST /orders

---

# 📈 Melhorias Futuras

- Implementação de Microservices
- Integração com AWS
- Cache com Redis
- Deploy online
---

# 👨‍💻 Autor

Desenvolvido por Gabriel Benford

LinkedIn:
```bash
https://www.linkedin.com/in/gabriel-loureiro-benford/
```

GitHub:
```bash
https://github.com/GabrielBenford
```
