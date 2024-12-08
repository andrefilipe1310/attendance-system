# Attendance System

Este é um sistema de controle de frequência de estudantes, desenvolvido com Spring Boot e PostgreSQL, e está hospedado no Railway. Abaixo, você encontrará as instruções para baixar, iniciar o projeto localmente e visualizar as rotas disponíveis.

## Diagramas
Para visualizar o diagrama de Classes e de ER clique aqui: [Diagramas](https://github.com/andrefilipe1310/attendance-system/blob/main/ionicattendancedocs/models/classes/Diaramas.pdf)


```mermaid
classDiagram
    class Student {
        Long id
        String name
        String studentImage
        String email
        String password
        String phone
        LocalDate birth
        +OneToOne: Guardian mother
        +OneToOne: Guardian father
        +OneToMany: List<Absence> absences
    }

    class Absence {
        Long id
        LocalDate absenceDay
        boolean isJustificationValid
        String justificationText
    }

    class Guardian {
        Long id
        String name
        String phone
    }

    class User implements UserDetails {
        Long id
        String email
        String password
        UserRoles roles
        +Collection getAuthorities()
        +void update(UserRequestDTO userRequestDTO)
        +UserResponseDTO toDTO()
    }

    class UserRequestDTO {
        <<record>>
        String email()
        String password()
    }

    class UserResponseDTO {
        <<record>>
        Long id
        String email
    }

    class StudentResponseDTO {
        <<record>>
        Long id
        String name
        String studentImage
        LocalDate birth
        Guardian mother
        Guardian father
        String email
        String password
        String phone
        List<Absence> absences
        int totalAbsences
        int totalMonthAbsences
        int totalWeekAbsences
        Map<LocalDate,Boolean> monthFrequency
        Map<LocalDate,Boolean> weeklyFrequency
        LocalDate todayDate
    }

    Student "1" --> "many" Absence
    Student "1" --> "1" Guardian : mother
    Student "1" --> "1" Guardian : father
    User "1" --> "1" UserRequestDTO
    User "1" --> "1" UserResponseDTO

```
## Casos de Uso e casos de Teste

[Casos de Uso](https://github.com/andrefilipe1310/attendance-system/blob/main/ionicattendancedocs/usecase/Casos%20de%20uso.pdf)
[Casos de Teste](https://github.com/andrefilipe1310/attendance-system/blob/main/ionicattendancedocs/usecase/Casos%20de%20Teste.pdf)

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:
- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- [PostgreSQL](https://www.postgresql.org/download/)

## Como baixar o projeto

Para baixar o projeto, execute os seguintes comandos no terminal:

```bash
# Clone o repositório
git clone https://github.com/andrefilipe1310/attendance-system.git

# Entre na pasta do projeto
cd attendance-system

## Configurações de Banco de Dados

O projeto já está configurado para usar o Railway como banco de dados em produção, mas caso queira rodar localmente, você pode configurar o `application.properties` para apontar para o seu próprio banco PostgreSQL.

### Usando Banco de Dados Local

Edite o arquivo `src/main/resources/application.properties` com as seguintes configurações:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
## Usando Railway

O projeto está hospedado no Railway, permitindo que você veja as rotas e funcionalidades em produção sem precisar rodar o projeto localmente.

#### Passos para acessar o sistema no Railway:

1. Acesse a aplicação diretamente pela URL: [Attendance System no Railway](https://attendance-system-production.up.railway.app).
2. Utilize uma ferramenta como o [Swagger](http://attendance-system-production.up.railway.app/swagger-ui.html) para explorar as rotas da API e visualizar a documentação interativa.

#### Verificando Rotas

Para visualizar as rotas e realizar testes nas APIs, você pode acessar a documentação do Swagger disponível diretamente no ambiente de produção:

- [Swagger - Attendance System](http://attendance-system-production.up.railway.app/swagger-ui.html)

Com essa interface, você pode explorar todas as rotas disponíveis no sistema, fazer requisições e ver as respostas das APIs diretamente pela interface do Swagger.

#### Observações

- Certifique-se de que o banco de dados está acessível e a aplicação rodando corretamente no Railway antes de realizar requisições.
- Todas as funcionalidades do sistema estão ativas na versão de produção.

## Lista de bibliotecas, frameworks utilizados para desenvolver o projeto

- Ionic
- SpringBoot
- OpenCV
- AngularJS

## Fluxo de trabalho

Para ter mais informações sobre o fluxo de trabalho utilizados no projeto é só visulizar o mindmap [AQUI](https://github.com/andrefilipe1310/attendance-system/blob/main/ionicattendancedocs/models/mindmap/Fluxo%20de%20Trabalho.pdf)

## Mockup

Para visualizar o prótotipo construido para esse projeto é só acessar a pasta [MOCKUP](https://github.com/andrefilipe1310/attendance-system/tree/main/ionicattendancedocs/models/mockup)

### Contribuindo

Para contribuir com o projeto acesse o arquivo [CONTRIBUTING.md](https://github.com/andrefilipe1310/attendance-system/blob/main/CONTRIBUTING.md)
para mais informações!

#### Comandos do Git para contribuir

````
git clone https://github.com/andrefilipe1310/attendance-system.git

git checkout -b nomebranch

git status

git add .

git commit -a -m "Alterações Salvas"

git push origm nomebranch
`````

### Autores

###### Funções:

- **Scrum Master**: Estephani Germana
- **Gerente de Configurações**: Manuella Jatobá
- **Documentador**: Amanda Lima e Ariano Souza
- **Desenvolvedores**: Ayrton Leonardo e André Filipe

### Licença

A licença do projeto é a Creative Commons para mais informações acessar o arquivo [LICENCE](https://github.com/andrefilipe1310/attendance-system/blob/main/LICENSE).


