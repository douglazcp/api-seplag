# **Projeto Spring Boot com PostgreSQL, MinIO e Docker Compose** 
# **Candidato Douglas Carrijo Pena**

## **Tecnologias Utilizadas**
* Spring Boot: Framework Java para construção da aplicação backend.
* PostgreSQL: Banco de dados relacional.
* Docker Compose: Ferramenta para definir e rodar aplicativos multi-containers (PostgreSQL e MinIO).
* JWT: Autenticação baseada em JSON Web Tokens.
* MinIO: Serviço de armazenamento de objetos para upload de imagens.

## **Estrutura do Projeto**

### Configuração do Spring Boot Initializr: 
O projeto foi inicializado através do Spring Boot Initializr com as seguintes dependências:

* Spring Web
* Spring Data JPA
* Spring Security
* PostgreSQL Driver
* Lombok
* Spring Boot DevTools
* JWT (dependência para autenticação)
 
### Configuração do Docker Compose 
O Docker Compose foi configurado para rodar três containers:

* PostgreSQL: Banco de dados relacional.
* PgAdmin: Interface para facilitar o gerenciamento do PostgreSQL.
* MinIO: Serviço de armazenamento de objetos para upload de imagens.

O arquivo docker-compose.yml configura todos os containers necessários, incluindo as credenciais de acesso ao MinIO.

Diagrama de Classes: As classes foram criadas conforme o diagrama de classes a seguir:

![img.png](img.png)

## Execução

1. [x] `docker run -d --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=seplag -p 5432:5432 postgres:latest`
   * ✅ Iniciará um PostgreSQL na porta 5432 com banco e usuário configurados.

2. [x] `docker run -p 9000:9000 -p 9001:9001 --name minio -e "MINIO_ROOT_USER=admin" -e "MINIO_ROOT_PASSWORD=admin123" minio/minio server /data --console-address ":9001"`
   * ✅ Iniciará um MinIO na porta 9000 e o painel de administração na 9001.

3. [x] Acessar o MinIO

   * http://localhost:9000
   * **Usuário**: admin
   * **Senha**: admin123

4. [x] Crie um bucket para armazenar os objetos S3.