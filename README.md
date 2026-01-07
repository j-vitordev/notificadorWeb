# 📦 Notificador Web

Projeto backend desenvolvido em **Java + Spring Boot**, com banco de dados **MySQL**, totalmente **dockerizado**.  
A aplicação simula o fluxo de **pedidos**, permitindo listar, atualizar status, realizar soft delete e enviar notificações por SMS.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Docker
- Docker Compose
- Angular (frontend separado)
- Twilio (envio de SMS)

---

## 🎯 Objetivo do projeto

O objetivo deste projeto é demonstrar:

- Criação de uma API REST com Spring Boot
- Uso de **Docker** para padronizar o ambiente
- Integração entre backend e banco de dados via Docker Compose
- Implementação de **soft delete**
- Comunicação com frontend Angular
- Envio de notificações via SMS

---

## 🧱 Arquitetura

- **Backend**: Spring Boot (container Docker)
- **Banco de dados**: MySQL (container Docker)
- **Frontend**: Angular (consome a API via HTTP)

O backend e o banco são orquestrados com **Docker Compose**, garantindo que qualquer pessoa consiga rodar o projeto facilmente.

---

## 🐳 Como rodar o projeto com Docker

### Pré-requisitos
- Docker instalado
- Docker Compose instalado

---

### 1️⃣ Clonar o repositório

```bash
git clone <url-do-repositorio>
cd notificadorWeb


### 2️⃣ Configurar variáveis de ambiente

Configure as variáveis necessárias no seu sistema (ou em um .env), como:

DB_URL_DOCKER=jdbc:mysql://mysql:3306/notificador
DB_USERNAME_DOCKER=root
DB_PASSWORD_DOCKER=root

TWILIO_ACCOUNT_SID=xxxx
TWILIO_AUTH_TOKEN=xxxx
TWILIO_SMS_FROM=xxxx
SMS_DESTINO=xxxx


### 3️⃣ Subir a aplicação

docker compose up --build

Esse comando:

Constrói a imagem do backend

Sobe o MySQL

Inicializa o banco

Inicia a API REST

### 4️⃣ Acessar a API

Backend disponível em:

http://localhost:8080

Exemplo de endpoint:

GET /pedidos

🗄️ Banco de dados

O banco MySQL é inicializado automaticamente via Docker com um script SQL (init.sql), contendo:

Criação da tabela pedido

Dados iniciais para testes

O banco utiliza soft delete, controlado pelo campo ativo.

🧠 Regras de negócio principais

Listagem retorna apenas pedidos ativos

Exclusão de pedido é feita via soft delete

Atualização de status dispara envio de SMS

Produtos são simulados via regras de negócio no backend

🖥️ Frontend

O frontend Angular consome o backend via HTTP:

http://localhost:8080/pedidos

Mesmo não estando dockerizado, ele se comunica normalmente com o backend rodando no Docker.

📌 Observações

O projeto foi desenvolvido com foco em aprendizado e boas práticas

A modelagem de produtos é simplificada (simulação)

Pode ser facilmente evoluído para incluir entidade Produto

👤 Autor

Projeto desenvolvido por Vitor
Foco em aprendizado, arquitetura e boas práticas com Java, Spring Boot, Testes Unitários e Docker.