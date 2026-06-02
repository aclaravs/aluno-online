# Projeto Aluno Online

## Sobre o Projeto

O **Aluno Online** é uma API REST desenvolvida com Java e Spring Boot, criada com o objetivo de simular um sistema acadêmico simples. A aplicação permite gerenciar alunos, professores, disciplinas e matrículas, utilizando banco de dados PostgreSQL para armazenar as informações.

O sistema foi desenvolvido como atividade prática da disciplina, aplicando conceitos de API REST, arquitetura em camadas, persistência de dados com Spring Data JPA e testes de requisições utilizando o Insomnia.

---

## Funcionalidades Implementadas

O projeto possui as seguintes funcionalidades:

### Aluno

* Cadastrar aluno
* Listar alunos
* Buscar aluno por ID
* Atualizar dados de aluno
* Deletar aluno

### Professor

* Cadastrar professor
* Listar professores
* Buscar professor por ID
* Atualizar dados de professor
* Deletar professor

### Disciplina

* Cadastrar disciplina
* Listar disciplinas
* Buscar disciplina por ID
* Atualizar disciplina
* Deletar disciplina
* Buscar disciplinas por professor
* Buscar disciplinas por aluno

### Matrícula de Aluno

* Realizar matrícula de aluno em disciplina
* Listar matrículas de um aluno
* Atualizar notas do aluno
* Trancar matrícula
* Destrancar matrícula
* Emitir histórico do aluno
* Deletar matrícula

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Insomnia
* DBeaver
* IntelliJ IDEA
* Git e GitHub

---

## Arquitetura do Projeto

O projeto foi organizado utilizando uma arquitetura em camadas. Essa divisão facilita a manutenção do código, separa responsabilidades e deixa a aplicação mais organizada.

A estrutura principal do projeto é composta pelas seguintes camadas:

```text
src/main/java/br/com/alunoonline/api
├── controller
├── dtos
├── model
├── repository
└── service
```

---

## Descrição das Camadas

### Controller

A camada `controller` é responsável por receber as requisições HTTP enviadas pelo Insomnia ou por qualquer outro cliente da API.

Nessa camada ficam os endpoints do sistema, como:

* `/alunos`
* `/professores`
* `/disciplinas`
* `/matriculas`

Os controllers recebem as requisições e encaminham as operações para a camada de serviço.

Exemplos de classes:

* `AlunoController`
* `ProfessorController`
* `DisciplinaController`
* `MatriculaAlunoController`

---

### Service

A camada `service` contém as regras de negócio da aplicação.

Ela é responsável por executar as operações solicitadas pelos controllers, como salvar, atualizar, buscar e remover registros. Também ficam nessa camada métodos mais específicos, como atualizar notas, trancar matrícula, destrancar matrícula e emitir histórico.

Exemplos de classes:

* `AlunoService`
* `ProfessorService`
* `DisciplinaService`
* `MatriculaAlunoService`

---

### Repository

A camada `repository` é responsável pela comunicação com o banco de dados.

As interfaces dessa camada utilizam o Spring Data JPA, permitindo realizar operações no PostgreSQL sem a necessidade de escrever SQL manualmente para as funções básicas.

Exemplos de interfaces:

* `AlunoRepository`
* `ProfessorRepository`
* `DisciplinaRepository`
* `MatriculaAlunoRepository`

---

### Model

A camada `model` contém as entidades do sistema. Essas classes representam as tabelas criadas no banco de dados.

As principais entidades do projeto são:

* `Aluno`
* `Professor`
* `Disciplina`
* `MatriculaAluno`

Cada entidade possui seus atributos, relacionamentos e anotações JPA utilizadas para o mapeamento com o banco PostgreSQL.

---

### DTOs

A pasta `dtos` contém classes utilizadas para transportar dados em algumas requisições e respostas da API.

Os DTOs ajudam a organizar melhor os dados que entram e saem do sistema, evitando expor diretamente todas as informações das entidades em determinados casos.

Exemplos:

* `AtualizarNotasRequestDTO`
* `DisciplinasAlunoResponseDTO`
* `HistoricoAlunoResponseDTO`

---

## Banco de Dados

O banco de dados utilizado no projeto foi o **PostgreSQL**.

O banco criado para a aplicação foi:

```text
aluno_online
```

Configuração utilizada no arquivo `application.properties`:

```properties
spring.application.name=Aluno Online

spring.datasource.url=jdbc:postgresql://localhost:5432/aluno_online
spring.datasource.username=postgres
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

As tabelas foram geradas automaticamente pelo Hibernate a partir das entidades do projeto.

Tabelas principais criadas:

* `aluno`
* `professor`
* `disciplina`
* `matricula_aluno`

---

## Endpoints da API

### Endpoints de Aluno

```text
POST   http://localhost:8080/alunos
GET    http://localhost:8080/alunos
GET    http://localhost:8080/alunos/{id}
PUT    http://localhost:8080/alunos/{id}
DELETE http://localhost:8080/alunos/{id}
```

### Endpoints de Professor

```text
POST   http://localhost:8080/professores
GET    http://localhost:8080/professores
GET    http://localhost:8080/professores/{id}
PUT    http://localhost:8080/professores/{id}
DELETE http://localhost:8080/professores/{id}
```

### Endpoints de Disciplina

```text
POST   http://localhost:8080/disciplinas
GET    http://localhost:8080/disciplinas
GET    http://localhost:8080/disciplinas/{id}
PUT    http://localhost:8080/disciplinas/{id}
DELETE http://localhost:8080/disciplinas/{id}
GET    http://localhost:8080/disciplinas/professor/{professorId}
GET    http://localhost:8080/disciplinas/aluno/{alunoId}
```

### Endpoints de Matrícula

```text
POST   http://localhost:8080/matriculas
GET    http://localhost:8080/matriculas/aluno/{alunoId}
PATCH  http://localhost:8080/matriculas/atualizarNotas/{matriculaId}
PATCH  http://localhost:8080/matriculas/trancar/{matriculaId}
PATCH  http://localhost:8080/matriculas/destrancar/{matriculaId}
GET    http://localhost:8080/matriculas/emitirHistorico/{alunoId}
DELETE http://localhost:8080/matriculas/{id}
```

---

## Exemplos de Requisições

### Cadastro de Aluno

```json
{
  "nomeCompleto": "Vitoria Silva",
  "cpf": "12345678900",
  "email": "vitoria@email.com"
}
```

### Cadastro de Professor

```json
{
  "nomeCompleto": "Carlos Henrique",
  "cpf": "98765432100",
  "email": "carlos@email.com"
}
```

### Cadastro de Disciplina

```json
{
  "nome": "Programação 2",
  "cargaHoraria": 80,
  "professor": {
    "id": 1
  }
}
```

### Cadastro de Matrícula

```json
{
  "aluno": {
    "id": 1
  },
  "disciplina": {
    "id": 1
  }
}
```

### Atualização de Notas

```json
{
  "nota1": 8.5,
  "nota2": 9.0
}
```

---

## Prints das Requisições no Insomnia

Abaixo estão os prints das requisições realizadas no Insomnia para testar a API.

### Criar Aluno

![Criar Aluno](prints/criar-aluno.png)

### Listar Alunos

![Listar Alunos](prints/listar-alunos.png)

### Atualizar Aluno

![Atualizar Aluno](prints/atualizar-aluno.png)

### Deletar Aluno

![Deletar Aluno](prints/deletar-aluno.png)

### Criar Professor

![Criar Professor](prints/criar-professor.png)

### Listar Professores

![Listar Professores](prints/listar-professores.png)

### Atualizar Professor

![Atualizar Professor](prints/atualizar-professor.png)

### Deletar Professor

![Deletar Professor](prints/deletar-professor.png)

### Criar Disciplina

![Criar Disciplina](prints/criar-disciplina.png)

### Listar Disciplinas

![Listar Disciplinas](prints/listar-disciplinas.png)

### Atualizar Disciplina

![Atualizar Disciplina](prints/atualizar-disciplina.png)

### Deletar Disciplina

![Deletar Disciplina](prints/deletar-disciplina.png)

### Criar Matrícula

![Criar Matrícula](prints/criar-matricula.png)

### Atualizar Notas

![Atualizar Notas](prints/atualizar-notas.png)

### Trancar Matrícula

![Trancar Matrícula](prints/trancar-matricula.png)

### Destrancar Matrícula

![Destrancar Matrícula](prints/destrancar-matricula.png)

### Emitir Histórico

![Emitir Histórico](prints/emitir-historico.png)

### Deletar Matrícula

![Deletar Matrícula](prints/deletar-matricula.png)

---

## Prints do DBeaver

Abaixo estão os prints do banco de dados no DBeaver, mostrando as tabelas com os dados utilizados durante os testes.

### Tabela Aluno

![Tabela Aluno](prints/tabela-aluno-dbeaver.png)

### Tabela Professor

![Tabela Professor](prints/tabela-professor-dbeaver.png)

---

## Como Executar o Projeto

Para executar o projeto, siga os passos abaixo:

1. Clone o repositório:

```bash
git clone https://github.com/aclaravs/aluno-online.git
```

2. Abra o projeto no IntelliJ IDEA.

3. Crie um banco de dados no PostgreSQL com o nome:

```text
aluno_online
```

4. Configure o arquivo `application.properties` com o usuário e a senha do seu PostgreSQL.

5. Execute a classe principal do projeto:

```text
ApiApplication
```

6. Com a aplicação rodando, teste os endpoints no Insomnia utilizando a porta:

```text
http://localhost:8080
```

---

## Conclusão

O projeto Aluno Online permitiu colocar em prática o desenvolvimento de uma API REST utilizando Java, Spring Boot e PostgreSQL. A aplicação foi estruturada em camadas, tornando o código mais organizado e facilitando a separação entre controle das requisições, regras de negócio e acesso ao banco de dados.

Com os testes realizados no Insomnia e a visualização dos dados no DBeaver, foi possível confirmar o funcionamento das operações de cadastro, listagem, atualização, exclusão e gerenciamento de matrículas.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aluno_online
spring.datasource.username=postgres
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true