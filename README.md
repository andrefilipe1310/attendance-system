# Attendance System

Este é um sistema de controle de frequência de estudantes, desenvolvido com Spring Boot e PostgreSQL, e está hospedado no Railway. Abaixo, você encontrará as instruções para baixar, iniciar o projeto localmente e visualizar as rotas disponíveis.

## Diagrama de classes
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

