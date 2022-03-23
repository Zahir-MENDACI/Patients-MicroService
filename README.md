# Patients-MicroService

API Patients

## Members
 - AZIZE Younes
 - MENDACI Zahir

# Install, Configure & Run

```bash
# Without Docker

# Run the app.
mvn spring-boot:run

```

```bash
# With Docker

# Switch to the branch "docker"

mvn clean install

docker build -t patients-docker:latest .

docker container run --network patients-doctors --name patients -p 8080:8080 -d patients-docker

```
