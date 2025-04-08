# 📚 Bookshop App

Sistema completo para gerenciamento de livros com funcionalidades de aluguel, utilizando arquitetura fullstack com:

- **Frontend:** React + Vite + Tailwind CSS + shadcn/ui
- **Backend:** Spring Boot (Java 17) + PostgreSQL
- **Ambiente:** Docker + Docker Compose

---

## ✨ Funcionalidades

- Listagem de livros com filtros e paginação
- Visualização de detalhes do livro
- Aluguel e devolução de livros
- Validação para impedir exclusão de livros alugados
- API REST documentada com Swagger
- Comunicação com backend via API
- Exibição de mensagens com toasts (usando a lib `sonner`)

---

## 🛠 Tecnologias Utilizadas

### Frontend

- [React](https://react.dev/)
- [Vite](https://vitejs.dev/)
- [Tailwind CSS](https://tailwindcss.com/)
- [shadcn/ui](https://ui.shadcn.com/)
- [Sonner](https://sonner.emilkowal.dev/) (toasts)

### Backend

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Hibernate JPA](https://hibernate.org/orm/)
- [Swagger (SpringDoc)](https://springdoc.org/)

### DevOps

- Docker
- Docker Compose

---

## 🧪 Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## 🚀 Como executar

### Clone o repositório

```bash
git clone https://github.com/seu-usuario/bookshop-app.git
cd bookshop-app
```

### Suba os containers com Docker Compose

```bash
docker-compose down --volumes
docker-compose build --no-cache
docker-compose up
```

### Acesse os serviços

- Frontend: [http://localhost:3000](http://localhost:3000)
- Backend: [http://localhost:8080](http://localhost:8080)
- Swagger UI: [http://localhost:8080/custom-swagger-ui](http://localhost:8080/custom-swagger-ui)
