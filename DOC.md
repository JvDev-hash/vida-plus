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
  "nome": "Dr. João Silva",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com",
  "dataNascimento": "1985-05-15T00:00:00.000Z",
  "especialidade": "Cardiologia",
  "crm": "CRM-SP 123456",
  "status": "ATIVO",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": 123,
    "complemento": "Apto 45",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
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

#### Buscar Profissional por ID

Retorna os dados de um profissional específico.

**Endpoint:** `GET /pessoas/profissional/{id}`

**Autenticação:** Não requerida

**Path Parameters:**
- `id`: ID do profissional

**Resposta de Sucesso (200 OK):**
```json
{
  "nome": "Dr. João Silva",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com",
  "dataNascimento": "1985-05-15T00:00:00.000Z",
  "especialidade": "Cardiologia",
  "crm": "CRM-SP 123456",
  "status": "ATIVO"
}
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Profissional não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro interno do servidor

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
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com",
  "dataNascimento": "1985-05-15",
  "sexo": "Masculino",
  "escolaridade": "Superior Completo",
  "ocupacao": "Engenheiro",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": 123,
    "complemento": "Apto 45",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
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

#### Buscar Paciente por ID

Retorna os dados de um paciente específico.

**Endpoint:** `GET /pessoas/paciente/{id}`

**Autenticação:** Não requerida

**Path Parameters:**
- `id`: ID do paciente

**Resposta de Sucesso (200 OK):**
```json
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com",
  "dataNascimento": "1985-05-15T00:00:00.000Z",
  "sexo": "Masculino",
  "escolaridade": "Superior Completo",
  "ocupacao": "Engenheiro"
}
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Paciente não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro interno do servidor

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

**Body (LeitoDTO):**
```json
{
  "numero": "101",
  "descricao": "CAMA 15CM",
  "tipo": "CAMA",
  "alaMedica": "ENFERMARIA",
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

**Body (LeitoDTO):** Mesmo formato do cadastro
```json
{
  "numero": "101",
  "descricao": "CAMA 15CM",
  "tipo": "CAMA",
  "alaMedica": "ENFERMARIA",
  "status": "DISPONIVEL"
}
```

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
  "nome": "Máscara Cirúrgica N95",
  "descricao": "Máscara de proteção respiratória N95 para uso hospitalar",
  "quantidade": 500,
  "quantidadeMinima": 100,
  "unidade": "Unidade",
  "status": "disponivel"
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

---

### Prontuário

#### Admissão de Paciente

Cria um novo prontuário e registra a admissão de um paciente.

**Endpoint:** `POST /prontuario/admissao`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Body (ProntuarioDTO):**
```json
{
  "dataAdmissao": "2024-01-15T10:00:00.000Z",
  "motivoAdmissao": "Dor abdominal intensa",
  "diagnostico": "Apendicite aguda",
  "historicoMedico": "Paciente sem histórico de cirurgias",
  "evolucao": "Paciente em observação",
  "encaminhamento": "Aguardando avaliação cirúrgica",
  "conduta": "Repouso e medicação analgésica",
  "pacienteId": 1,
  "profissionalId": 1
}
```

**Resposta de Sucesso (201 CREATED):**
```
"Paciente admisso com sucesso!"
```

**Respostas de Erro:**
- `500 INTERNAL_SERVER_ERROR`: Erro ao realizar admissão

#### Internação de Paciente

Registra a internação de um paciente em um leito.

**Endpoint:** `PUT /prontuario/internacao/{prontuarioId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `prontuarioId`: ID do prontuário

**Body (InternacaoPacienteDTO):**
```json
{
  "dataInternacao": "2024-01-15T14:00:00.000Z",
  "motivoInternacao": "Necessidade de cirurgia",
  "leitoId": 5
}
```

**Resposta de Sucesso (200 OK):**
```
"Paciente internado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao internar paciente

#### Editar Prontuário

Atualiza os dados de um prontuário existente.

**Endpoint:** `PUT /prontuario/editar/{prontuarioId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `prontuarioId`: ID do prontuário a ser editado

**Body (ProntuarioDTO):** Mesmo formato da admissão
```json
{
  "dataAdmissao": "2024-01-15T10:00:00.000Z",
  "motivoAdmissao": "Dor abdominal intensa",
  "diagnostico": "Apendicite aguda",
  "historicoMedico": "Paciente sem histórico de cirurgias",
  "evolucao": "Paciente em observação",
  "encaminhamento": "Aguardando avaliação cirúrgica",
  "conduta": "Repouso e medicação analgésica",
  "pacienteId": 1,
  "profissionalId": 1
}
```

**Resposta de Sucesso (200 OK):**
```
"Prontuario editado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao editar prontuário

#### Alta de Paciente

Registra a alta de um paciente.

**Endpoint:** `PUT /prontuario/alta/{prontuarioId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `prontuarioId`: ID do prontuário

**Body (AltaPacienteDTO):**
```json
{
  "dataAlta": "2024-01-20T10:00:00.000Z",
  "motivoAlta": "Alta médica após recuperação cirúrgica"
}
```

**Resposta de Sucesso (200 OK):**
```
"Paciente dado de alta com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao registrar alta

#### Registro de Óbito

Registra o óbito de um paciente.

**Endpoint:** `PUT /prontuario/obito/{prontuarioId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `prontuarioId`: ID do prontuário

**Body (ObitoPacienteDTO):**
```json
{
  "dataObito": "2024-01-18T08:00:00.000Z",
  "causaObito": "Complicações pós-operatórias"
}
```

**Resposta de Sucesso (200 OK):**
```
"Registro de óbito realizado com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao registrar óbito

#### Transferir Paciente

Transfere um paciente para outro leito.

**Endpoint:** `PUT /prontuario/transferir/{prontuarioId}`

**Autenticação:** Requerida (permissão diferente de VIEW)

**Path Parameters:**
- `prontuarioId`: ID do prontuário

**Body:**
```json
10
```

**Nota:** O body deve conter apenas o ID do leito (Long) para onde o paciente será transferido.

**Resposta de Sucesso (200 OK):**
```
"Paciente transferido com sucesso!"
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao transferir paciente

#### Listar Prontuários

Retorna uma lista paginada de prontuários cadastrados.

**Endpoint:** `GET /prontuario/listar`

**Autenticação:** Não requerida

**Query Parameters:**
- `pageNo` (opcional, padrão: 0): Número da página
- `pageSize` (opcional, padrão: 10): Tamanho da página

**Resposta de Sucesso (200 OK):**
```json
{
  "content": [
    {
      "numeroProntuario": "PRT-2024-0001",
      "dataAdmissao": "2024-01-15T10:00:00.000Z",
      "motivoAdmissao": "Dor abdominal intensa",
      "diagnostico": "Apendicite aguda",
      "historicoMedico": "Paciente sem histórico de cirurgias",
      "evolucao": "Paciente em observação",
      "encaminhamento": "Aguardando avaliação cirúrgica",
      "conduta": "Repouso e medicação analgésica",
      "dataInternacao": "2024-01-15T14:00:00.000Z",
      "dataAlta": null,
      "motivoInternacao": "Necessidade de cirurgia",
      "motivoAlta": null,
      "dataObito": null,
      "causaObito": null,
      "leito": {
        "id": 5,
        "numero": "101",
        "descricao": "CAMA 15CM",
        "tipo": "CAMA",
        "alaMedica": "ENFERMARIA",
        "status": "OCUPADO"
      },
      "paciente": {
        "nome": "João Silva",
        "cpf": "123.456.789-00",
        "telefone": "(11) 98765-4321",
        "email": "joao.silva@email.com",
        "dataNascimento": "1985-05-15T00:00:00.000Z",
        "sexo": "Masculino",
        "escolaridade": "Superior Completo",
        "ocupacao": "Engenheiro"
      },
      "profissional": {
        "nome": "Dr. João Silva",
        "cpf": "123.456.789-00",
        "telefone": "(11) 98765-4321",
        "email": "joao.silva@email.com",
        "dataNascimento": "1985-05-15T00:00:00.000Z",
        "especialidade": "Cardiologia",
        "crm": "CRM-SP 123456",
        "status": "ATIVO"
      }
    }
  ],
  "totalElements": 15,
  "totalPages": 2,
  "currentPage": 0
}
```

**Respostas de Erro:**
- `500 INTERNAL_SERVER_ERROR`: Erro ao listar prontuários

#### Buscar Prontuário por ID

Retorna os dados de um prontuário específico.

**Endpoint:** `GET /prontuario/listar/{prontuarioId}`

**Autenticação:** Não requerida

**Path Parameters:**
- `prontuarioId`: ID do prontuário

**Resposta de Sucesso (200 OK):**
```json
{
  "numeroProntuario": "PRT-2024-0001",
  "dataAdmissao": "2024-01-15T10:00:00.000Z",
  "motivoAdmissao": "Dor abdominal intensa",
  "diagnostico": "Apendicite aguda",
  "historicoMedico": "Paciente sem histórico de cirurgias",
  "evolucao": "Paciente em observação",
  "encaminhamento": "Aguardando avaliação cirúrgica",
  "conduta": "Repouso e medicação analgésica",
  "dataInternacao": "2024-01-15T14:00:00.000Z",
  "dataAlta": null,
  "motivoInternacao": "Necessidade de cirurgia",
  "motivoAlta": null,
  "dataObito": null,
  "causaObito": null,
  "leito": {
    "id": 5,
    "numero": "101",
    "descricao": "CAMA 15CM",
    "tipo": "CAMA",
    "alaMedica": "ENFERMARIA",
    "status": "OCUPADO"
  },
  "paciente": {
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "telefone": "(11) 98765-4321",
    "email": "joao.silva@email.com",
    "dataNascimento": "1985-05-15T00:00:00.000Z",
    "sexo": "Masculino",
    "escolaridade": "Superior Completo",
    "ocupacao": "Engenheiro"
  },
  "profissional": {
    "nome": "Dr. João Silva",
    "cpf": "123.456.789-00",
    "telefone": "(11) 98765-4321",
    "email": "joao.silva@email.com",
    "dataNascimento": "1985-05-15T00:00:00.000Z",
    "especialidade": "Cardiologia",
    "crm": "CRM-SP 123456",
    "status": "ATIVO"
  }
}
```

**Respostas de Erro:**
- `404 NOT_FOUND`: Prontuário não encontrado
- `500 INTERNAL_SERVER_ERROR`: Erro ao buscar prontuário