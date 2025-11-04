# Auth User Application
## Gerenciamento de Usuários com Integração Externa
### Projeto final do módulo 04 programação web - programa Desenvolva+ em parceria da Ada Tech com o MELI.

### 📘 Descrição Geral

O AuthUser é uma aplicação monolítica desenvolvida em Java com Spring Boot, projetada para o gerenciamento completo de usuários (CRUD) e integração com uma API externa (Dummy JSON).

A aplicação fornece endpoints RESTful documentados via Swagger (com autenticação Basic Auth) e utiliza o banco de dados H2 em ambiente local.

O projeto foi desenvolvido com foco em boas práticas de arquitetura, camadas bem definidas e integração modular entre fontes de dados internas e externas.

### ⚙️ Funcionalidades Principais


### 🔹 CRUD Completo — Banco de Dados Local (H2)

A aplicação expõe os seguintes endpoints principais para gerenciamento interno de usuários:

Método	Endpoint	Descrição:

```GET```	```/users```	Lista todos os usuários cadastrados paginados

```GET```	```/users/{id}```	Retorna um usuário pelo ID

```GET```	```/users/name/{name}```	Filtra usuários por nome

```POST```	```/users```	Cria um novo usuário

```PUT```	```/users/{id}```	Atualiza todos os dados de um usuário existente

```PATCH```	```/users/{id}```	Atualiza parcialmente os dados de um usuário

```DELETE```	```/users/{id}```	Remove um usuário do sistema

### 🔹 Integração com API Externa — Dummy JSON

A aplicação também possui uma camada de serviço externo para comunicação com a API pública Dummy JSON.

Os endpoints dessa integração seguem a mesma estrutura CRUD (exceto PATCH), permitindo consumir, criar, atualizar e remover dados diretamente da API externa.

Método	Endpoint Local	Ação Executada

```GET```	```/api/users```	Lista todos os usuários da Dummy JSON

```GET```	```/api/users/{id}```	Retorna um usuário específico da Dummy JSON

```POST```	```/users```	Cria um novo usuário na Dummy JSON

```PUT```	```/users/{id}```	Atualiza um usuário na Dummy JSON

```DELETE	``` ```/users/{id}```	Remove um usuário da Dummy JSON

### 🧱 Arquitetura e Camadas

O projeto segue o padrão de arquitetura em camadas, promovendo desacoplamento e legibilidade do código:

src/main/java/authuser/

 ├── config/

 │    ├── SwaggerConfig.java
 
 │

 ├── dtos/
 
 │    ├── UserDTO.java

 │    ├── UserDTODummyJson.java

 │    ├── UserResponseDTODummy.java
  
 │
 
 ├── entities/
 
 │    ├── User.java
 
 │    ├── UserDummyJson.java

 │
 
 ├── repositories/
 
 │    ├── UserRepository.java
 
 │    ├── DummyJsonClient.java 
  
 │
 
 ├── resources/exceptions/

 │    ├── StandardError.java

 │    ├── ResourceExceptionHandler.java

 │
 
 ├── resources/
 
 │    ├── UserResource.java

 │    ├── UserDummyResource.java
  
 │
 
 ├── services/exceptions/

 │    ├── ResourceNotFoundException.java

 │
 
 ├── services/
 
 │    ├── UserService.java
 
 │    ├── UserServiceDummyJson.java

 │
 
 ├── services/impl/

 │    ├── UserServiceImpl.java

 │    ├── UserServiceDummyJsonImpl.java

 ### 🧩 Tecnologias Utilizadas

 | Categoria                | Tecnologias                       |
| :----------------------- | :-------------------------------- |
| **Linguagem**            | Java 22+                          |
| **Framework Principal**  | Spring Boot                       |
| **Banco de Dados**       | H2 (em memória)                   |
| **Documentação da API**  | Swagger UI + OpenAPI              |
| **Autenticação**         | Basic Auth                        |
| **Client Open Feign**    | Open Feign (Spring)          |
| **Build Tool**           | Maven                             |
| **IDE Recomendada**      | IntelliJ IDEA / Eclipse / VS Code |

### 🔐 Autenticação — Basic Auth

O Swagger e os endpoints REST são protegidos por autenticação básica (Basic Auth).
Basta logar com um usuário e senha aleatório, pois não necessita de um usuário autenticado, está apenas simulando autenticação, para facilitar a avaliação/testes do instrutor do módulo atual.

### 🧰 Banco de Dados H2

A aplicação utiliza o H2 Database (em memória) para persistência de dados local.

Acesso ao Console H2:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:authuser

Usuário: sa

Senha:

### 📖 Documentação da API (Swagger)

Após executar o projeto, acesse o Swagger UI para visualizar e testar os endpoints:

```bash
http://localhost:8080/swagger-ui.html
```
### 🚀 Como Executar o Projeto

Pré-requisitos:

Java 22 +

Maven 4. 0. 0 +

Spring Boot 3. 3 .4 +

 Passos para execução:

 ```bash
git clone https://github.com/Igorgcf/projeto-final-programacao-web-devmais.git
```

Compilar e executar:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```bash
http://localhost:8080
```

### 🧪 Exemplos de Requisições

🔹 Criar Usuário (POST)

```bash
 POST /users
```

```json
{
  "name": "Igor",
  "lastName" : "Freitas",
  "phone" : "11 77070-7070",
  "email": "igor@email.com",
  "password": "1234567"
}
```

🔹 Filtrar Usuário por Nome (GET)
```bash
GET /users/name/{name}=Igor
```
### 👨‍💻 Autor

Igor Gonçalves de Freitas

📧 [igorgcf@outlook.com.br]

![image](https://portswigger.net/cms/images/82/40/cc98-article-220330-spring-cloud-main.png)
