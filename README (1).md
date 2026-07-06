# Sistema Acadêmico — Curso + Aluno

Projeto Full Stack (Spring Boot + Angular) desenvolvido para a avaliação prática de Programação Orientada a Objetos — IFG.

- **Back-end:** Spring Boot (Java), rodando em `http://localhost:8080`, context path `/sistema-academico`
- **Front-end:** Angular + Angular Material, rodando em `http://localhost:4200`

## Estrutura

```
backend/   -> API REST (Curso e Aluno)
frontend/  -> Aplicação Angular (Curso e Aluno)
```

## Pré-requisitos

- Java 17+ (o projeto usa Spring Boot 4 / parent 4.0.6)
- Maven (ou usar o `mvnw` incluído no projeto, se houver)
- Node.js 18+ e npm
- Angular CLI (`npm install -g @angular/cli`)
- PostgreSQL rodando localmente

## 1. Banco de dados

O `application.properties` do back-end está configurado para:

```properties
spring.datasource.url = jdbc:postgresql://localhost:5432/jdbc
spring.datasource.username = postgres
spring.datasource.password = aluno
```

Crie um banco chamado `jdbc` no PostgreSQL (ou ajuste essas propriedades para o seu ambiente). O Hibernate está com `ddl-auto=update`, então as tabelas (`aluno`, `curso`, `aluno_curso`) são criadas/atualizadas automaticamente ao subir a aplicação.

## 2. Rodando o back-end

Dentro da pasta do projeto back-end:

```bash
# usando o maven wrapper (se existir)
./mvnw spring-boot:run

# ou, se tiver o Maven instalado globalmente
mvn spring-boot:run
```

A API sobe em `http://localhost:8080/sistema-academico`.

Endpoints disponíveis:

| Método | URL                       | Descrição                     |
|--------|---------------------------|--------------------------------|
| GET    | /sistema-academico/cursos | Lista cursos                  |
| GET    | /sistema-academico/alunos | Lista alunos                  |
| GET    | /sistema-academico/alunos/{id} | Busca aluno por id       |
| POST   | /sistema-academico/alunos | Cadastra aluno (201 + Location)|
| PUT    | /sistema-academico/alunos/{id} | Atualiza aluno            |
| DELETE | /sistema-academico/alunos/{id} | Remove aluno (204)        |

## 3. Rodando o front-end

Dentro da pasta do projeto front-end:

```bash
npm install
ng serve
```

A aplicação sobe em `http://localhost:4200`.

Rotas disponíveis:

| Rota                    | Componente          |
|-------------------------|----------------------|
| /cursos                 | CursoListComponent   |
| /cursos/novo            | CursoFormComponent   |
| /cursos/editar/:id      | CursoFormComponent   |
| /alunos                 | AlunoListComponent   |
| /alunos/novo            | AlunoFormComponent   |
| /alunos/editar/:id      | AlunoFormComponent   |

## 4. Ordem recomendada para testar

1. Suba o PostgreSQL.
2. Suba o back-end (`mvn spring-boot:run`) e confirme que não há erros de conexão com o banco.
3. Suba o front-end (`ng serve`).
4. Acesse `http://localhost:4200/alunos` no navegador.
5. Teste cadastrar, editar e excluir um aluno pela interface.

## Observações

- O CORS já está liberado no back-end para `http://localhost:4200` (`@CrossOrigin`), então não é necessário nenhuma configuração extra de proxy no Angular.
- O campo `idaluno` é gerado automaticamente pelo banco (`@GeneratedValue`), por isso é opcional no model TypeScript (`aluno.ts`).
- Ao tentar buscar/atualizar um aluno com id inexistente, a API retorna `404 Not Found` (`ResourceNotFoundException`).
