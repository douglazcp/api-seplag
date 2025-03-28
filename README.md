# **Projeto Spring Boot com PostgreSQL, MinIO, JWT e Docker Compose** 
## **Candidato Douglas Carrijo Pena**

### **Tecnologias Utilizadas**
* Spring Boot: Framework Java para construção da aplicação backend.
* PostgreSQL: Banco de dados relacional.
* Docker Compose: Ferramenta para definir e rodar aplicativos multi-containers (PostgreSQL e MinIO).
* JWT: Autenticação baseada em JSON Web Tokens.
* MinIO: Serviço de armazenamento de objetos para upload de imagens.

### Configuração do projeto
O Docker Compose foi configurado para rodar dois containers:

* PostgreSQL: Banco de dados relacional.
* MinIO: Serviço de armazenamento de objetos para upload de imagens.

O arquivo docker-compose.yml configura todos os containers necessários, incluindo as credenciais de acesso ao MinIO.

Diagrama de Classes: As classes foram criadas conforme o diagrama de classes a seguir:

![img.png](img.png)

## Como executar o projeto

### Clonar o repositório
```bash
git clone https://github.com/douglazcp/api-seplag.git
cd api-seplag
```

### Iniciar o docker
#### Via  o docker-compose.yml
`docker-compose up -d`
#### Via terminal
* #### Iniciar um PostgreSQL na porta 5432 com banco e usuário configurados. 
  * `docker run -d --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=seplag -p 5432:5432 postgres:latest`

* #### Iniciar um MinIO na porta 9000 e o painel de administração na 9001.
    * `docker run -p 9000:9000 -p 9001:9001 --name minio -e "MINIO_ROOT_USER=admin" -e "MINIO_ROOT_PASSWORD=admin123" minio/minio server /data --console-address ":9001"`

### Acessar o MinIO
   * http://localhost:9000
   * **Usuário**: ``admin``
   * **Senha**: ``admin123``

* #### Criar um bucket para armazenar os objetos S3.
* `bucket-seplag-projeto-pratico`

### Autenticação com JWT

- A autenticação é feita via JWT (token expira em 5 minutos)
- Para gerar o token, use o endpoint:

### 🔹 `POST /auth/login`

```json
{
   "username":"admin",
   "password":"password"
}
```

### Estrutura do projeto
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── gov
│   │   │           └── mt
│   │   │               └── apiseplag
│   │   │                   ├── controller
│   │   │                   ├── dto
│   │   │                   ├── model
│   │   │                   ├── repository
│   │   │                   ├── security
│   │   │                   ├── service
│   │   │                   └── utils
│   │   └── resources
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── br
│               └── gov
│                   └── mt
│                       └── apiseplag
└── target
...
```