# Auth user — Gerenciamento de Usuários com Integração Externa

📘 Descrição Geral

O AuthUser é uma aplicação monolítica desenvolvida em Java com Spring Boot, projetada para o gerenciamento completo de usuários (CRUD) e integração com uma API externa (Dummy JSON).

A aplicação fornece endpoints RESTful documentados via Swagger (com autenticação Basic Auth) e utiliza o banco de dados H2 em ambiente local.

O projeto foi desenvolvido com foco em boas práticas de arquitetura, camadas bem definidas e integração modular entre fontes de dados internas e externas.

⚙️ Funcionalidades Principais

🔹 CRUD Completo — Banco de Dados Local (H2)

A aplicação expõe os seguintes endpoints principais para gerenciamento interno de usuários:

Método	Endpoint	Descrição

```GET```	```/users```	Lista todos os usuários cadastrados

```GET```	```/users/{id}```	Retorna um usuário pelo ID

```GET```	```/users/search?name={name}```	Filtra usuários por nome

```POST```	```/users```	Cria um novo usuário

```PUT```	```/users/{id}```	Atualiza todos os dados de um usuário existente

```PATCH```	```/users/{id}```	Atualiza parcialmente os dados de um usuário

```DELETE```	```/users/{id}```	Remove um usuário do sistema

🔹 Integração com API Externa — Dummy JSON

A aplicação também possui uma camada de serviço externo para comunicação com a API pública Dummy JSON.

Os endpoints dessa integração seguem a mesma estrutura CRUD (exceto PATCH), permitindo consumir, criar, atualizar e remover dados diretamente da API externa.

Método	Endpoint Local	Ação Executada

```GET```	```/api/users```	Lista todos os usuários da Dummy JSON

```GET```	```/api/users/{id}```	Retorna um usuário específico da Dummy JSON

```POST```	```/users```	Cria um novo usuário na Dummy JSON

```PUT```	```/users/{id}```	Atualiza um usuário na Dummy JSON

```DELETE	```/users/{id}```	Remove um usuário da Dummy JSON

🧱 Arquitetura e Camadas

O projeto segue o padrão de arquitetura em camadas, promovendo desacoplamento e legibilidade do código:

src/

 ├── entities/
 
 │    ├── User.java
 
 │    ├── UserDummyJson.java

 ├── dtos/
 
 │    ├── UserDTO.java

 │    ├── UserDTODummyJson.java

 │    ├── UserResponseDTODummy.java
 
 
 ├── repositories/
 
 │    ├── UserRepository.java
 
 │    ├── DummyJsonClient.java 
 
 ├── services/
 
 │    ├── UserService.java
 
 │    ├── UserServiceDummyJson.java
 
 ├── services/impl/

 │    ├── UserServiceImpl.java

 │    ├── UserServiceDummyJsonImpl.java

 
 
