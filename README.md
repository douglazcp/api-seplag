# **Projeto Spring Boot com PostgreSQL, MinIO, JWT e Docker Compose** 
## **Candidato Douglas Carrijo Pena**

### **Tecnologias Utilizadas**
* JWT: Autenticação baseada em JSON Web Tokens.
   * `A. Implementar mecanismo de autorização e autenticação, bem como não permitir acesso ao
endpoint a partir de domínios diversos do qual estará hospedado o serviço;`
   * `B. A solução de autenticação deverá expirar a cada 5 minutos e oferecer a possibilidade de
renovação do período;`
* Spring Boot: Framework Java para construção da aplicação backend.
   * `C. Implementar pelo menos os verbos post, put, get;` 
   * `D. Conter recursos de paginação em todas as consultas;`
* PostgreSQL: Banco de dados relacional.
   * `E. Os dados produzidos deverão ser armazenados no servidor de banco de dados previamente
     criado em container;`
* Docker Compose: Ferramenta para definir e rodar aplicativos multi-containers (PostgreSQL e MinIO).
   * `F. Orquestrar a solução final utilizando Docker Compose de modo que inclua todos os contêineres
     utilizados.`
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

- A autenticação é feita via JWT.
  - `A solução de autenticação deverá expirar a cada 5 minutos e oferecer a possibilidade de
    renovação do período.`
- Para gerar o token, use o endpoint:
   - ` Implementar mecanismo de autorização e autenticação, bem como não permitir acesso ao
endpoint a partir de domínios diversos do qual estará hospedado o serviço;`
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