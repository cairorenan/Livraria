# 📖 Sistema de Livraria - Spring Boot

Este é um sistema de livraria desenvolvido em **Java** utilizando o **Spring Boot**, com o objetivo de gerenciar usuários e livros, permitindo operações como **aluguel** e **devolução de livros**.

## 🚀 Funcionalidades

-  Cadastro de usuários
-  Cadastro de livros
-  Aluguel de livros por usuários
-  Devolução de livros
-  Controle de unidades disponíveis
-  Consulta de usuários e livros


## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de Dados: PostgreSQL
- Maven
- Lombok
- Docker

## ☑️Executar Aplicação (Docker)

```bash
# Clone o repositório
git clone https://github.com/cairorenan/Livraria.git
cd Livraria

# Suba os containers
docker-compose up --build
```

## 🔌Endpoints da API

### 📁 Cliente

| Método | Rota                        | Descrição                     |
|--------|-----------------------------|-------------------------------|
| POST   | `/cliente`                  | Cadastrar um novo cliente     |
| GET    | `/cliente`                  | Listar todos os clientes      |
| GET    | `/cliente/{id}`             | Buscar cliente por ID         |
| PUT    | `/cliente/editar/{id}`      | Editar dados de um cliente    |
| DELETE | `/cliente/deletar/{id}`     | Deletar cliente por ID        |

---

### 📘 Livro

| Método | Rota                        | Descrição                     |
|--------|-----------------------------|-------------------------------|
| POST   | `/livro`                    | Cadastrar um novo livro       |
| GET    | `/livro`                    | Listar todos os livros        |
| GET    | `/livro/{id}`               | Buscar livro por ID           |
| PUT    | `/livro/editar/{id}`        | Editar dados de um livro      |
| DELETE | `/livro/deletar/{id}`       | Deletar livro por ID          |

---

### 🔄 Aluguel / Devolução

| Método | Rota                                             | Descrição                      |
|--------|--------------------------------------------------|--------------------------------|
| POST   | `/alugar/{id_cliente}/{id_livro}/{unidades}`     | Alugar unidades de um livro    |
| POST   | `/devolver/{id_cliente}/{id_livro}/{unidades}`   | Devolver unidades de um livro  |