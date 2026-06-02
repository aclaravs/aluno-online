# Aluno Online

## Explicação do Projeto

O projeto Aluno Online é uma API REST desenvolvida em Java com Spring Boot. O sistema tem como objetivo gerenciar alunos, professores, disciplinas e matrículas acadêmicas.

A aplicação permite realizar o cadastro, consulta, atualização e exclusão de alunos, professores e disciplinas. Além disso, possui funcionalidades relacionadas à matrícula de alunos em disciplinas, como lançamento de notas, trancamento, destrancamento e emissão de histórico.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- IntelliJ IDEA
- Insomnia
- DBeaver

## Descrição da Arquitetura Utilizada

O projeto utiliza uma arquitetura em camadas, separando as responsabilidades da aplicação.

### Controller

A camada Controller é responsável por receber as requisições HTTP feitas pelo usuário através do Insomnia. Ela define os endpoints da API e encaminha as chamadas para a camada Service.

Exemplos:
- AlunoController
- ProfessorController
- DisciplinaController
- MatriculaAlunoController

### Service

A camada Service contém as regras de negócio da aplicação. Nela ficam os métodos responsáveis por cadastrar, listar, atualizar, deletar e executar ações específicas, como atualizar notas e trancar matrícula.

Exemplos:
- AlunoService
- ProfessorService
- DisciplinaService
- MatriculaAlunoService

### Repository

A camada Repository é responsável pela comunicação com o banco de dados. Ela utiliza o Spring Data JPA para realizar operações no PostgreSQL.

Exemplos:
- AlunoRepository
- ProfessorRepository
- DisciplinaRepository
- MatriculaAlunoRepository

### Model

A camada Model representa as entidades do sistema, ou seja, as tabelas criadas no banco de dados.

Entidades:
- Aluno
- Professor
- Disciplina
- MatriculaAluno

## Detalhamento do Código

### Aluno

A entidade Aluno representa os estudantes cadastrados no sistema. Ela possui informações como id, nome completo, CPF e e-mail.

O CRUD de Aluno permite:
- Cadastrar aluno
- Listar alunos
- Buscar aluno por id
- Atualizar aluno
- Deletar aluno

### Professor

A entidade Professor representa os professores cadastrados no sistema. Ela possui informações como id, nome completo, CPF e e-mail.

O CRUD de Professor permite:
- Cadastrar professor
- Listar professores
- Buscar professor por id
- Atualizar professor
- Deletar professor

### Disciplina

A entidade Disciplina representa as disciplinas disponíveis no sistema. Ela possui informações como id, nome, carga horária e professor responsável.

O CRUD de Disciplina permite:
- Cadastrar disciplina
- Listar disciplinas
- Buscar disciplina por id
- Atualizar disciplina
- Deletar disciplina

### MatriculaAluno

A entidade MatriculaAluno representa a matrícula de um aluno em uma disciplina. Ela relaciona o aluno com a disciplina e permite controlar notas e status da matrícula.

Funcionalidades implementadas:
- Matricular aluno em disciplina
- Listar matrículas de um aluno
- Atualizar notas
- Trancar matrícula
- Destrancar matrícula
- Emitir histórico
- Remover matrícula

## Banco de Dados

O projeto utiliza o PostgreSQL como banco de dados.

Configuração utilizada no arquivo application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aluno_online
spring.datasource.username=postgres
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true