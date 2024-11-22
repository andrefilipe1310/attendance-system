# Primeiro estágio: construção
FROM maven:3.8.5-openjdk-17 AS build

# Diretório de trabalho
WORKDIR /app

# Copia os arquivos
COPY pom.xml .
COPY src ./src

# Executa o comando de build
RUN mvn install -e -X

# Segundo estágio: imagem final
FROM openjdk:17-jdk-slim

# Copia o jar da construção anterior
COPY --from=build /app/target/attendance-system-0.0.1-SNAPSHOT.jar /app/app.jar

# Diretório de trabalho
WORKDIR /app

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
