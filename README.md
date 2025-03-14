# VetCareAPI

## 📋 Descrição do Projeto

O **VetCareAPI** é uma aplicação backend desenvolvida com Java e Spring Boot, voltada para a gestão de uma clínica veterinária. A aplicação está organizada em três módulos principais que representam os pilares do sistema:

- 🐾 **Adoção**: Gerenciamento de animais disponíveis para adoção.
- 🩺 **Clínica Veterinária**: Administração de pacientes, consultas e veterinários.
- 🛍️ **Loja**: Controle de produtos e vendas.

---

## ⚙️ Funcionalidades

### 🐾 Módulo de Adoção
- Cadastro de animais disponíveis para adoção.
- Listagem e consulta de animais cadastrados.
- Registro do processo de adoção por interessados.
- Acompanhamento do status das adoções.

### 🩺 Módulo de Clínica Veterinária
- Cadastro de pacientes (animais atendidos).
- Agendamento e gerenciamento de consultas veterinárias.
- Histórico médico de atendimentos e procedimentos.
- Cadastro e gerenciamento de veterinários.

### 🛍️ Módulo de Loja
- Cadastro de produtos (ração, medicamentos, acessórios etc).
- Controle de estoque com atualização automática.
- Processamento de vendas de produtos da loja.

---

## 🧪 Tecnologias e Ferramentas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Flyway (migração de banco de dados)**
- **Swagger (documentação da API)**
- **Lombok**
- **JUnit & Mockito (testes automatizados)**
- **Maven (gerenciador de dependências)**

---

## 📁 Estrutura do Projeto

```
VetCareAPI/
│
├── Adocao/               → Funcionalidades de adoção de pets
├── Clinica_Veterina/     → Funcionalidades da clínica veterinária
└── Store/                → Funcionalidades da loja (produtos e vendas)
```

Cada módulo segue boas práticas de arquitetura RESTful, com separação clara entre camadas: **Controller**, **Service**, **Repository**, **DTOs** e **Entities**.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 21 instalado
- MySQL em execução
- Maven instalado

### Passos:
```bash
# Clone o repositório
git clone https://github.com/LeonardoChiarelli/-PROJETO-VetCare-/VetCareAPI.git

# Acesse o diretório do projeto
cd VetCareAPI

# Configure o banco de dados no arquivo application.properties

# Execute as migrações Flyway
mvn flyway:migrate

# Inicie o projeto
mvn spring-boot:run
```

### Acessar a documentação da API:
- `http://localhost:8080/swagger-ui.html`

---

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma **issue** ou enviar um **pull request** com sugestões, melhorias ou correções.

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License**. Consulte o arquivo `LICENSE` para mais detalhes.
