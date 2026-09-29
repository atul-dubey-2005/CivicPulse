# CivicPulse

**Intelligent Civic Issue Resolution & Verification Platform**

CivicPulse is a complete student-project implementation for reporting, routing, assigning, resolving and verifying civic issues. It is designed as an operational workflow system rather than a basic CRUD complaint form.

## Stack
- Frontend: React + Vite
- Backend: Java 17 + Spring Boot + JPA + Spring Security
- Database: PostgreSQL 16
- AI: Python + FastAPI + scikit-learn
- Deployment: Docker Compose

## Repository map
- `frontend/` React application
- `backend/` Spring Boot API
- `ai-service/` similarity service
- `database/` PostgreSQL schema and seed scripts
- `tests/` frontend/API/AI test assets
- `documentation/` academic and technical documentation
- `project-report/` DOCX/PDF report, user manual and developer guide
- `diagrams/` editable Graphviz sources and rendered PNG/SVG diagrams
- `assets/ui-screenshots/` representative UI screenshots
- `deployment/` Nginx and deployment configuration

## Local development
Prerequisites: Java 17+, Maven 3.9+, Node.js 20+, npm, Python 3.11+, PostgreSQL 16+ or Docker.

### Docker
1. Copy `.env.example` to `.env`.
2. Set a strong `JWT_SECRET`.
3. Run `docker compose up --build`.
4. Open `http://localhost:5173`.
5. API health: `http://localhost:8080/api/health`.
6. AI health: `http://localhost:8000/health`.

### Demo accounts
Run `database/sample-data.sql` after `schema.sql` and `seed.sql`. Demo password is `password` and must never be used in production.

## Manual development
### Backend
```bash
cd backend
mvn test
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm test
npm run dev
```

### AI
```bash
cd ai-service
python -m venv .venv
# activate venv
pip install -r requirements.txt
uvicorn app:app --reload --port 8000
```

## Security
Passwords are BCrypt-hashed. JWT and RBAC protect APIs. Citizens are restricted to their own complaints. Production requires HTTPS, secret management, rate limiting, secure object storage and file validation.

## Verification limitation
The project package contains static checks and tests, but a clean runtime build depends on Maven, Docker, PostgreSQL and browser tooling being available. The verification report records exactly what was and was not runtime-verified.

## Documentation
Start with `documentation/README.md`, then `documentation/18_Deployment_Guide/README.md` and `project-report/CivicPulse_Project_Report.pdf`.
