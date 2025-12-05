# VidaPlus

Sistema de gerenciamento hospitalar desenvolvido em Spring Boot para administração de recursos hospitalares, incluindo leitos, suprimentos, profissionais, pacientes e prontuários.

## Tecnologias

- Java 17
- Spring Boot 3.4.12
- Spring Security
- Spring Data JPA
- H2 Database (em memória)
- JWT (JSON Web Tokens) 
- Auth0
- Maven
- Lombok

## Funcionalidades

- Autenticação e autorização baseada em JWT com cookies HTTP-only
- Gerenciamento de usuários com controle de permissões (ADMIN, VIEW)
- Gerenciamento de pessoas (profissionais e pacientes)
- Gerenciamento de leitos
- Gerenciamento de suprimentos
- Gerenciamento de prontuários
- Console H2 para acesso ao banco de dados em desenvolvimento

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

## Configuração

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd vida-plus
```

2. Crie um arquivo `.env` na raiz do projeto e na pasta resources com as seguintes variáveis:
```
SECRET_KEY=sua-chave-secreta-para-jwt
ISSUER=vidaplus
```

3. Compile o projeto:
```bash
mvn clean install
```

## Executando a Aplicação

Execute a aplicação usando Maven:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8081`

## Banco de Dados

O projeto utiliza H2 Database em memória. O console H2 está disponível em:
- URL: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:mem:vidaplusdb`
- Username: `sa`
- Password: `password`

## Usuário Padrão

Ao iniciar a aplicação, um usuário administrador é criado automaticamente:
- Email: `admin@vidaplus.com`
- Senha: `admin123`
- Permissão: `ADMIN`

## Documentação da API

> **Consulte a [documentação completa da API](DOC.md) para detalhes de endpoints e exemplos de uso.**

---

## Segurança

A aplicação utiliza Spring Security com autenticação baseada em JWT. O token é armazenado em um cookie HTTP-only chamado `vptoken`.

### Permissões

- `VIEW`: Permissão apenas de leitura (GET)
- `ADMIN`: Permissão completa (GET, POST, PUT, DELETE)

### Regras de Autorização

- Métodos GET são públicos para todos os endpoints principais
- Métodos POST e PUT requerem autenticação e permissão diferente de VIEW
- Métodos DELETE requerem permissão ADMIN
- O endpoint `/login/autenticar` é público
- O console H2 (`/h2-console/**`) é público apenas em desenvolvimento

## Estrutura do Projeto

```
src/main/java/vidaplus/project/
├── configuration/     # Configurações de segurança e filtros
├── controller/       # Controladores REST
├── DTO/              # Data Transfer Objects
├── init/             # Inicialização de dados
├── model/            # Entidades JPA
├── repository/       # Repositórios Spring Data
└── service/          # Lógica de negócio
```

## Desenvolvimento

Para desenvolvimento, a aplicação está configurada com:
- Logging em nível DEBUG para Spring Security
- SQL visível no console
- Console H2 habilitado
- Spring Boot DevTools para hot reload

## Testes

Execute os testes com:
```bash
mvn test
```

## Licença

Este projeto é um projeto de demonstração.

