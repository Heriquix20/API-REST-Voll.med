<h1>Voll.med</h1> 

<h2>Projeto API-REST com Java e Spring Boot</h2>

<img width="400" height="300" src= "images/voll.med.png"> 

## Descrição

Voll.med é uma API RESTful desenvolvida em Java utilizando o framework Spring Boot. Este projeto tem como objetivo gerenciar médicos e pacientes, permitindo marcar consultas de acordo com as validações definidas. A autenticação dos usuários é feita através de tokens JWT, garantindo um controle de acesso seguro. O projeto também inclui migrations para o banco de dados MySQL e documentação interativa com Swagger.

## Funcionalidades

- **Autenticação de Usuários**: Implementação de autenticação usando tokens JWT.
- **Controle de Acesso**: Controle de permissões e acesso para diferentes tipos de usuários (médicos, pacientes, administradores).
- **Gestão de Médicos e Pacientes**: CRUD (Create, Read, Update, Delete) para médicos e pacientes.
- **Agendamento de Consultas**: Marcação de consultas médicas com validações específicas.
- **Migrations**: Uso de migrations para gerenciar o esquema do banco de dados MySQL.
- **Documentação**: Documentação da API usando Swagger.

## Tecnologias Utilizadas

<img  alt= "java" src = "https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img  alt= "spring" src = "https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img  alt= "mysql" src = "https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white"><img  alt= "token" src = "https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink"><img  alt= "flyway" src = "https://img.shields.io/badge/Flyway-CC0200.svg?style=for-the-badge&logo=Flyway&logoColor=white"><img  alt= "swagger" src = "https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black"><img  alt= "security" src = "https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white">


- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para criar a API REST.
- **Spring Security**: Para a implementação de autenticação e controle de acesso.
- **JWT (JSON Web Token)**: Para autenticação baseada em tokens.
- **Hibernate**: ORM para interagir com o banco de dados.
- **Flyway**: Ferramenta de migrations para gerenciar o banco de dados.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **Swagger**: Ferramenta para documentação da API.

## Pré-requisitos

- **Java 11** ou superior
- **Maven** para gerenciamento de dependências
- **MySQL** para o banco de dados

## Configuração do Projeto

## Contribuição
Sinta-se à vontade para contribuir com o projeto através de pull requests. Antes de contribuir, por favor, abra uma issue para discutir as mudanças propostas.

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

## Contato
Para mais informações, entre em contato com hcgv1@hotmail.com

### Clonando o Repositório

```bash
git clone https://github.com/seu-usuario/vollmed.git
cd vollmed
