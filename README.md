# Sistema Acadêmico — Curso e Aluno

Avaliação Prática — Programação Orientada a Objetos
Instituto Federal de Goiás — IFG
Bacharelado em Sistemas de Informação — 5º semestre

**Aluna:** Sophia Lannah

## Descrição

Projeto Full Stack (Spring Boot + Angular) que implementa o CRUD completo da entidade `Curso` (já fornecida) e da entidade `Aluno` (implementada seguindo o mesmo padrão), conforme especificado na avaliação.

## Estrutura do repositório

```
sistema-academico-prova/
├── backend/    -> API REST em Spring Boot (Curso e Aluno)
└── frontend/   -> Aplicação Angular (Curso e Aluno)
```

## Tecnologias

- **Back-end:** Java, Spring Boot, Spring Data JPA, PostgreSQL
- **Front-end:** Angular (standalone components), Angular Material, RxJS

## Como rodar

### 1. Banco de dados

Configuração em `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url = jdbc:postgresql://localhost:5432/jdbc
spring.datasource.username = postgres
spring.datasource.password = aluno
```

Crie um banco chamado `jdbc` no PostgreSQL (ou ajuste as propriedades acima para o seu ambiente). O Hibernate está com `ddl-auto=update`, então as tabelas são criadas/atualizadas automaticamente.

### 2. Back-end

```bash
cd backend
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080/sistema-academico`.

### 3. Front-end

```bash
cd frontend
npm install
ng serve
```

A aplicação sobe em `http://localhost:4200`.

## Endpoints — Aluno

| Método | URL                             | Descrição                        |
|--------|----------------------------------|------------------------------------|
| GET    | /sistema-academico/alunos        | Lista alunos                       |
| GET    | /sistema-academico/alunos/{id}   | Busca aluno por id                 |
| POST   | /sistema-academico/alunos        | Cadastra aluno (201 + header Location) |
| PUT    | /sistema-academico/alunos/{id}   | Atualiza aluno                     |
| DELETE | /sistema-academico/alunos/{id}   | Remove aluno (204 No Content)      |

## Rotas — Front-end

| Rota                    | Componente          |
|--------------------------|----------------------|
| /cursos                  | CursoListComponent   |
| /cursos/novo             | CursoFormComponent   |
| /cursos/editar/:id       | CursoFormComponent   |
| /alunos                  | AlunoListComponent   |
| /alunos/novo             | AlunoFormComponent   |
| /alunos/editar/:id       | AlunoFormComponent   |

## Observações

- CORS liberado para `http://localhost:4200` (`@CrossOrigin`) em todos os controllers.
- `findById` de `Aluno` lança `ResourceNotFoundException` (404) quando o id não existe.
- `AlunoService` é injetado via construtor em `AlunoResource`.
- O model `aluno.ts` tem `idaluno` opcional, já que o id é gerado pelo banco.
- O formulário de aluno usa `mat-select` para o campo sexo (M/F) e `input type="date"` para a data de nascimento, com pré-preenchimento via `patchValue` em modo edição.
