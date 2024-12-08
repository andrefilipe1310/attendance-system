# Attendance System

## Descrição do projeto
>Este é um sistema de controle de frequência de estudantes, desenvolvido com Spring Boot e PostgreSQL, e está hospedado no Railway.

## Como baixar o repositório

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

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:
- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- [PostgreSQL](https://www.postgresql.org/download/)

## Lista de bibliotecas, frameworks utilizados para desenvolver o projeto

- Ionic
- SpringBoot
- OpenCV
- AngularJS

## Fluxo de trablho

Para ter mais informações sobre o fluxo de trabalho utilizados no projeto é só visulizar o mindmap [AQUI](https://github.com/andrefilipe1310/attendance-system/blob/main/ionicattendancedocs/models/mindmap/Fluxo%20de%20Trabalho.pdf)


## Como contribuir

Primeiro, obrigado por dedicar seu tempo para contribuir!

A seguir, temos um conjunto de diretrizes para contribuir com nosso Attendance System.
Essas diretrizes são apenas sugestões, não regras rígidas. Use seu bom senso e fique à vontade para propor alterações neste documento por meio de um pull request.

## Enviando Problemas (Issues)

* Você pode criar um problema [aqui](https://github.com/andrefilipe1310/attendance-system/issues/new/choose), incluindo o máximo de detalhes possível no seu relato.
* Informe as versões do Java e Maven que você está utilizando.
* Sempre que possível, inclua capturas de tela são extremamente úteis.
* Descreva o comportamento esperado e mencione outros lugares onde você já viu esse comportamento.
* Realize uma busca nas issues para verificar se um problema semelhante já foi enviado.

## Enviando Pull Requests

* Primeiramente, faça um Fork do projeto.
* Faça merge da branch principal (Main) para evitar conflitos.
* Envie seu Pull Request.
>Obrigado pela ajuda!

## Comandos do Git para contribuir

````
git clone https://github.com/andrefilipe1310/attendance-system.git

git checkout -b nomebranch

git status

git add .

git commit -a -m "Alterações Salvas"

git push origm nomebranch
`````

