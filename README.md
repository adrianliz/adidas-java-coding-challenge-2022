# What to do?

The repo contains a skeleton of 4 Spring Boot applications, plus a Docker Compose configuration which spins up the
following working environment.

<img width="365" alt="image" src="https://unregisteredUser-images.githubusercontent.com/15728394/199699196-3bf20be2-cc51-4718-8cc2-454c8397c9d4.png">

- _Public Service_ in the main entry point to our system, and the only accesible one to the public.
- _Priority Sale Service_ is the service containing the priority sale logic.
- _Adi-Club Service_ contains the information of our adiClub members. e-mail, points, registration date...
- _Email Service_ sends a confirmation mail to the lucky ones.

We would like you to implement the following:

- Subscription to the sale using the Public Service, and the internal logic, using all internal services. Reordering the
  queue upon new registrations, selecting the next winner...
- The communications among the different services, either sync or async.
- API documentation using Swagger, API BluePrint or the tool you feel more comfortable with.

Do not forget our challenge Non Functional Requirements:

- **Security** for non-public services
- Proper **exception handling** and REST responses.​
- Unit **testing** for meaningful code (business logic / services).​

We encourage you to take a look at our architectural principles. And of course, you have total freedom to propose or/and
implement the improvements you want! Changes on the architecture, Introducing new services and/or containers, Reactive
APIs, Distributed logging.... **Your creativity is more than welcome!**

# Technical Decisions

- I'm following a simple hexagonal or ports and adapters clean architecture on each microservice.

  This allows to develop a testable code (DIP of SOLID) with high change tolerance (OCP of SOLID)
  and with low code coupling (we can change any infrastructure dependency at any time).

  For this case, I assume that Spring Framework is part of the domain to simplify the development.

- Each microservice only know the existence of the public service or gateway.

  This implies high cohesion and independence inside each microservice.

- The public service or gateway is the responsible for coordination between the different microservices.

- The public service use Keycloak as an authentication server.

  This allows to have a centralized and reliable authentication server.

- The public service only allows unauthorized access to the priority sale service.

- The priority sale service uses [OAuth2 client credentials flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow) 
  to access internal protected resources through the public gateway.

# Architecture diagram

![architecture.svg](/docs/architecture.svg)

# User Stories

- [X] Add sale subscription: A user (adiClub or not) should be able to subscribe to the sale.
- [X] Generate next sale access:
  An internal system (front-end, CLI, etc.) should be able to retrieve the next user email that is allowed to access
  into the sale.
- [X] Validate if user email was already notified: A user only can be notified once.
- [X] Send mail on next sale access: A user should receive an email when she/he is allowed to access into the sale.

# Technical improvements

- [X] Use api gateway to decouple microservices.
- [X] Use asynchronous communication between microservices with spring webflux (reactive web client).
- [X] Practical error handling with spring webflux.
- [X] Unit testing of priority sale service.
- [X] Security between microservices with JWT authentication based on [Oauth2](https://oauth.net/2/).
- [X] Document public endpoints with OpenAPI 3.0 (using springdoc).

# Future TO-DOs:

Due to lack of time, I'm not able to implement all the features I would like to implement.

- [] Add integration tests.
- [] Add e2e tests.
- [] Add docker compose for production environment.
- [] Use kafka instead of Spring WebFlux for asynchronous communication based on domain events/query bus/command bus.

# What would you need?

The code requires the following tools:

- Maven 3.8.4
- JDK11
- A Docker container engine: docker desktop, podman, rancher or any other

# How to build & run the project

### Build jar files

`mvn clean install`

### Build keycloak image on MAC M1

Execute `./keycloak/build-keycloak-image-m1.zsh`

### Build docker images & start the containers (locally)

`docker-compose --env-file=.env.local up -d`

### Build docker images & start the containers

Execute `./keycloak/create-admin-user.zsh $USER $PASSWORD` to create the admin user of keycloak.

Create a new service account on keycloak.

Create a `.env` file with the following content:

```bash
KEYCLOAK_DB_USER=?
KEYCLOAK_DB_PASSWORD=?
PRIORITY_SALE_CLIENT_ID=?
PRIORITY_SALE_CLIENT_SECRET=?
```

`docker-compose up -d`

### Cleanup project

Using docker compose you need to run the following command:
`docker-compose down --rmi`

Using podman you should first stop the compose environment:
`docker-compose down`
And then you will need to delete the images manually.

# Useful commands

### Access Open API documentation

In order to see UI documentation you can write the following url in your browser:
`http://localhost:8080/swagger-ui.html`

### Watch public-service logs

`docker logs -f adidas-be-challenge-publicservice`
