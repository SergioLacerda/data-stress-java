# Getting Started

## Prerequisites

- JDK 17+
- Gradle

## Getting Started

### 1. Build the Application

build it using Gradle:

```bash
./gradlew clean build
```

## run application
to start app run:

```bash
./gradlew bootRun
```

## Kafka via docker
up kafka:

```bash
docker-compose up
```
### 3. Sending Messages

try with strings:

```bash
curl -X POST http://localhost:8080/api/send/10000

--OR
curl -X POST http://localhost:8080/api/sendBuffered/10000

--OR
curl -X POST http://localhost:8080/api/sendRandom/10000
```

### 4. Shell script tips
Repeat calls on n1 means seconds to call again, in this example will call each second

```bash
watch -n1 | curl -X POST http://localhost:8080/api/send/2000
```