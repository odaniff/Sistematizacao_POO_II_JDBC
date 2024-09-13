Este projeto é uma aplicação Java para gerenciamento de contatos, utilizando uma lista encadeada em memória e armazenamento persistente em um banco de dados PostgreSQL. O projeto integra o uso de JDBC (Java Database Connectivity) para interagir com o banco de dados.

_Requisitos_
- Java JDK 11 ou superior
- PostgreSQL 13 ou superior
- Biblioteca JDBC PostgreSQL 

_Configuração do Ambiente_
- Instalar o Java

Certifique-se de que o Java JDK está instalado em seu sistema. Você pode verificar isso com o comando:

- Instalar o PostgreSQL

Baixe e instale o PostgreSQL a partir do site oficial do PostgreSQL. Após a instalação, crie um banco de dados e um usuário para o projeto.

- Configurar o Banco de Dados
3.1 Criar o Banco de Dados
Execute o seguinte comando no terminal do PostgreSQL para criar o banco de dados:

_CREATE DATABASE "contacts-api";_

3.2 Criar a Tabela
Conecte-se ao banco de dados e execute o comando SQL abaixo para criar a tabela contacts:

_CREATE TABLE contacts (
    name VARCHAR(100) PRIMARY KEY,
    phonenumber VARCHAR(50),
    email VARCHAR(100)
);_

- Configurar o JDBC
Baixar o Driver JDBC
Baixe o driver JDBC para PostgreSQL a partir do site do PostgreSQL JDBC. Adicione o arquivo JAR do driver ao classpath do seu projeto.

- Configurar a Conexão
A classe Conexao.java é responsável por estabelecer a conexão com o banco de dados. Configure a URL, usuário e senha conforme necessário:

_private static final String url = "jdbc:postgresql://localhost:5432/contacts-api";
private static final String user = "postgres";
private static final String password = "root";_

Certifique-se de que esses parâmetros correspondem às suas configurações do PostgreSQL.
