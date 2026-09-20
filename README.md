
# 🧮 NLP-to-SQL Agent (Semantic Query Understanding Platform)

This project implements a production-grade SQL Agent for a complex Knowledge domain using:
- Spring Boot 4+, 
- Java 25,
- PGVector, 
- and Spring AI.

The **goal is not simple text-to-SQL** generation.

The **goal is reliable semantic query understanding for a domain** where:

* the schema is large
* users use abbreviations, aliases, and partial values
* the same business term may exist in multiple attributes
* business questions often span multiple subdomains

Because of this, the architecture uses:

**Planner → Semantic Resolver → SQL Generator → Validator → Executor → Optional HITL**

instead of:

**User Question → LLM → SQL**

This service integrates with the **AI Orchestrator**, enabling users to query internal databases conversationally and receive precise answers augmented by LLM reasoning.

---

## Implementation Details

### 🧩 Key Features

* Try not expose raw schema directly to the LLM - Use Either semantic business views 
OR Tables List depending on the "bounded context"
* Static Semantic resolution (in the future moved on to PGVector)
* Ambiguity must be resolved before SQL generation - using HITL(Human-In-The-Loop) or predefined rules
* LLM should not guess joins

# High-Level Architecture

```text
User Question
    ↓
questionParser.parseQuestion(question) as a result QuestionParserResult
    ↓
Semantic Resolver (questionClassifier.classify(question, parserResult);)
    ↓
(HITL if needed)
    ↓
questionProcessor.process(conversationId,questionId, plannerResult); -> SQL generator, Execution Layer (if fails during request then we do 1 attempt to fix syntax issue of generated query using LLM)
    ↓
Response Formatter (nlpToSqlSummaryService.getFinalResponse(conversationId,
                questionId, question, responses, classifierOutput);)
```



---

## Getting Started

### 🧱 Technology Stack

| Layer      | Technology               |
| ---------- |--------------------------|
| Backend    | Java 25, Spring Boot 4   |
| AI         | Spring AI (OpenAI model) |
| Data       | PostgreSQL               |
| Infra      | Docker                   |
| Build Tool | Gradle                   |

---

### ⚙️ How to Run Locally

### Prerequisites
- Java 25 (optional)
- npm (to build UI) (optional)
- Docker engine (*mandatory* to run dockernized app)
- bash console (*mandatory*)

#### 1. Clone the Repository

```bash
git clone https://github.com/MarinaPimenova/ti-sql-agent.git
cd ti-sql-agent
```

#### 2. Configure Environment Variables

- Before running docker-compose you need set up the following Environment variables in the `.env` file under config folder:
  see `docker/config/env.example`
- Open a terminal, navigate to the directory (`<ROOT_FOLDER>/docker`) <br/>
containing docker-compose.yml file, and run the following commands:

#### 3. Run PostgreSQL Locally

```shell
cd <ROOT_FOLDER>/docker
docker rm -v -f $(docker ps -qa)
docker rmi -f IMAGE_ID
docker compose down -v --remove-orphans

docker compose -f ./docker-compose.yml --env-file env up
```

#### 4. Run the Application

```bash
./gradlew build
./gradlew bootRun
```

#### 5. Example API Call

```bash
curl -X POST http://localhost:8088/api/v1/docs?conversationId=<UUID> \
  -H "Content-Type: application/json" \
  -H "Authentication: Bearer XXX" \
  -d '{"questionId": <questionId>, "question": "How many questions were created in 2026?"}'
```

---

## 🧩 Related Services

* **AI (Orchestrator)** – Consumes the output of Text-to-SQL Agent and merges results from other RAG agents.
* **AI Assistant UI (SPA)** – Renders responses in a conversational chat-style interface.

---
## Related questions:


