### Autenticação

#### Autenticar Usuário

Autentica um usuário e retorna um token JWT armazenado em cookie HTTP-only.

**Endpoint:** `POST /login/autenticar`

**Autenticação:** Não requerida

**Body:**
```json
{
  "email": "admin@vidaplus.com",
  "senha": "admin123"
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "nome": "Administrador",
  "permissao": "ADMIN"
}
```

**Cookie retornado:**
- Nome: `vptoken`
- HttpOnly: true
- MaxAge: 86400 segundos (24 horas)

**Respostas de Erro:**
- `404 NOT_FOUND`: Usuário ou senha incorretos
- `500 INTERNAL_SERVER_ERROR`: Erro interno do servidor

---

### Usuários

#### Listar Usuários

Retorna uma lista paginada de usuários cadastrados no sistema.

**Endpoint:** `GET /usuarios/listar`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Exemplo de Requisição:**
```
GET /usuarios/listar?pageNo=0&pageSize=10
```

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [...],
  "totalElements": 10,
  "totalPages": 1,
  "currentPage": 0
}
```

#### Cadastrar Usuário

Cria um novo usuário no sistema.

**Endpoint:** `POST /usuarios/cadastrar`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body:**
```json
{
  "nome": "João Silva",
  "email": "joao@vidaplus.com",
  "senha": "senha123",
  "permissao": "ADMIN",
  "status": "ATIVO"
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Usuario cadastrado com sucesso!"
```

**Respostas de Erro:**
- `500 INTERNAL_SERVER_ERROR`: Erro ao cadastrar usuário

#### Editar Usuário

Atualiza os dados de um usuário existente.

**Endpoint:** `PUT /usuarios/{id}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `id`: ID do usuário a ser editado

**Body:**
```json
{
  "nome": "João Silva Atualizado",
  "email": "joao@vidaplus.com",
  "senha": "novaSenha123",
  "permissao": "ADMIN",
  "status": "ATIVO"
}
```

**Resposta de Sucesso (200 OK):**
```
"Usuario editado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Usuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao editar usuário

#### Inativar Usuário

Inativa um usuário no sistema.

**Endpoint:** `PUT /usuarios/inativar/{id}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `id`: ID do usuário a ser inativado

**Resposta de Sucesso (200 OK):**
```
"Usuario inativado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Usuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao inativar usuário

---

### Pessoas

#### Cadastrar Profissional

Cria um novo profissional no sistema.

**Endpoint:** `POST /pessoas/profissional`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body:**
```json
{
  "nome": "Dr. Maria Santos",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "email": "maria@vidaplus.com",
  "dataNascimento": "1980-05-15",
  "especialidade": "Cardiologia",
  "crm": "CRM123456",
  "status": "ATIVO",
  "endereco": {
    "rua": "Rua Exemplo",
    "numero": "123",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01234-567"
  }
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Profissional cadastrado com sucesso!"
```

#### Listar Profissionais

Retorna uma lista paginada de profissionais cadastrados.

**Endpoint:** `GET /pessoas/profissional`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [...],
  "totalElements": 5,
  "totalPages": 1,
  "currentPage": 0
}
```

#### Editar Profissional

Atualiza os dados de um profissional existente.

**Endpoint:** `PUT /pessoas/profissional/{id}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `id`: ID do profissional a ser editado

**Body:** Mesmo formato do cadastro

**Resposta de Sucesso (200 OK):**
```
"Profissional atualizado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Profissional não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao atualizar profissional

#### Mudar Status do Profissional

Altera o status de um profissional.

**Endpoint:** `PUT /pessoas/profissional/status/{id}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `id`: ID do profissional

**Body:**
```
"INATIVO"
```

**Resposta de Sucesso (200 OK):**
```
"Profissional status mudado com sucesso!"
```

#### Cadastrar Paciente

Cria um novo paciente no sistema.

**Endpoint:** `POST /pessoas/paciente`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body:**
```json
{
  "nome": "João da Silva",
  "cpf": "987.654.321-00",
  "telefone": "(11) 91234-5678",
  "email": "joao.paciente@email.com",
  "dataNascimento": "1990-03-20",
  "status": "ATIVO",
  "endereco": {
    "rua": "Avenida Exemplo",
    "numero": "456",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01234-567"
  }
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Paciente cadastrado com sucesso!"
```

#### Listar Pacientes

Retorna uma lista paginada de pacientes cadastrados.

**Endpoint:** `GET /pessoas/paciente`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [...],
  "totalElements": 8,
  "totalPages": 1,
  "currentPage": 0
}
```

#### Editar Paciente

Atualiza os dados de um paciente existente.

**Endpoint:** `PUT /pessoas/paciente/{id}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `id`: ID do paciente a ser editado

**Body:** Mesmo formato do cadastro

**Resposta de Sucesso (200 OK):**
```
"Paciente atualizado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Paciente não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao atualizar paciente

---

### Leitos

#### Cadastrar Leito

Cria um novo leito no sistema.

**Endpoint:** `POST /leitos/cadastrar`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body:**
```json
{
  "numero": "101",
  "tipo": "ENFERMARIA",
  "status": "DISPONIVEL"
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Leito cadastrado com sucesso!"
```

#### Listar Leitos

Retorna uma lista paginada de leitos cadastrados.

**Endpoint:** `GET /leitos/listar`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [...],
  "totalElements": 20,
  "totalPages": 2,
  "currentPage": 0
}
```

#### Editar Leito

Atualiza os dados de um leito existente.

**Endpoint:** `PUT /leitos/editar/{leitoId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `leitoId`: ID do leito a ser editado

**Body:** Mesmo formato do cadastro

**Resposta de Sucesso (200 OK):**
```
"Leito editado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Leito não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao editar leito

#### Mudar Status do Leito

Altera o status de um leito.

**Endpoint:** `PUT /leitos/status/{leitoId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `leitoId`: ID do leito

**Body:**
```
"OCUPADO"
```

**Resposta de Sucesso (200 OK):**
```
"Leito status mudado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Leito não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao mudar status

---

### Suprimentos

#### Cadastrar Suprimento

Cria um novo suprimento no sistema.

**Endpoint:** `POST /suprimentos/cadastrar`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body:**
```json
{
  "nome": "Seringa 5ml",
  "descricao": "Seringa descartável de 5ml",
  "quantidade": 100,
  "unidadeMedida": "UNIDADE",
  "categoria": "MATERIAL_MEDICO"
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Suprimento cadastrado com sucesso!"
```

#### Listar Suprimentos

Retorna uma lista paginada de suprimentos cadastrados.

**Endpoint:** `GET /suprimentos/listar`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [...],
  "totalElements": 15,
  "totalPages": 2,
  "currentPage": 0
}
```

#### Atualizar Suprimento

Atualiza os dados de um suprimento existente.

**Endpoint:** `PUT /suprimentos/atualizar/{suprimentoId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `suprimentoId`: ID do suprimento a ser atualizado

**Body:** Mesmo formato do cadastro

**Resposta de Sucesso (200 OK):**
```
"Suprimento atualizado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Suprimento não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao atualizar suprimento