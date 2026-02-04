# 🚀 Urbana API – Desafio Técnico

API REST desenvolvida em **Java com Spring Boot** como parte do **desafio técnico do processo seletivo URBANA-PE**.  
A aplicação realiza o **gerenciamento de usuários e seus cartões**, com persistência em banco de dados MySQL e versionamento de schema via Flyway.

---

## 📌 Tecnologias utilizadas

- Java 17
- Spring Boot 3.0.0
- Spring Web
- Spring Data JPA
- MySQL
- Flyway (migração de banco)
- Springdoc OpenAPI (Swagger)
- Maven

---

## 📂 Estrutura do projeto
```text
src/main/java/com/projeto/urbana
 ├── controller
 │   ├── UsuarioController.java
 │   └── CartaoController.java
 ├── service
 ├── dto
 ├── model
 └── UrbanaApplication.java

 
▶️ Como executar o projeto
Pré-requisitos:
Java 17+
Maven 3.8+

MySQL em execução
Banco de dados
Crie o banco de dados no MySQL:
CREATE DATABASE usuario_urbana;
As migrações serão executadas automaticamente pelo Flyway ao iniciar a aplicação.


Executando a aplicação
# Clonar o repositório
git clone https://github.com/seu-usuario/urbana.git

# Entrar na pasta do projeto
cd urbana

# Executar
mvn spring-boot:run
A aplicação estará disponível em:

http://localhost:8080


⚙️ Configurações
Arquivo: application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/usuario_urbana
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
⚠️ Ajuste usuário e senha do banco conforme seu ambiente.


📖 Documentação da API (Swagger)
A documentação da API é gerada automaticamente com Springdoc OpenAPI.

Swagger UI:
👉 http://localhost:8080/swagger-ui.html

OpenAPI JSON:
👉 http://localhost:8080/v3/api-docs

📌 Endpoints principais
👤 Usuários
Método	Endpoint	Descrição
POST	/usuarios	Cadastrar usuário
GET	/usuarios	Listar usuários
GET	/usuarios/{id}	Consultar usuário
PUT	/usuarios/{id}	Atualizar usuário
DELETE	/usuarios/{id}	Remover usuário
💳 Cartões
Método	Endpoint	Descrição
POST	/usuarios/{id}/cartoes	Adicionar cartão ao usuário
GET	/usuarios/{usuarioId}/cartoes	Listar cartões do usuário
GET	/usuarios/{usuarioId}/cartoes/{cartaoId}	Consultar cartão
PUT	/usuarios/{usuarioId}/cartoes/{cartaoId}/status	Alterar status do cartão
DELETE	/usuarios/{usuarioId}/cartoes/{cartaoId}	Remover cartão

🌐 CORS
A API está configurada para aceitar requisições do frontend em:
http://localhost:4200

🧠 Padrões e boas práticas
Arquitetura em camadas (Controller, Service, DTO)

DTOs para entrada e saída de dados

Respostas padronizadas com ApiResponse

Uso de Flyway para versionamento do banco

API RESTful

Documentação automática com Swagger

👤 Autor
Projeto desenvolvido como parte do desafio técnico URBANA-PE.


📄 Licença
Este projeto é de uso exclusivo para fins de avaliação técnica.
