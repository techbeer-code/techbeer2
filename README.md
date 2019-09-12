# Alelo Tech & Beer - Microsserviços

Microsserviços utilizados na demostração apresentada durante o Techbeer.

<div align="center">
    <img src="/microservicos.png" /> 
</div>

## Config-Server
Responsável pela gestão das configurações dos demais serviços.

#### Configurações
- anotar a classe de inicialização com `@EnableConfigServer`
```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```
- especificar a porta e onde o repostório GIT com as configurações está localizado no arquivo `application.yml` 
```java
server:
  port: ${SERVER_PORT:8888}

spring:  
  application:
    name: config-server
  cloud:    
    config:      
      server:
        git:
          uri: ${CONFIG_URI:https://github.com/techbeer-code/techbeer2}
          searchPaths: configs
```

## Discovery-Server
Responsável pelo registro das instancias de serviços em execução.

#### Configurações
- anotar a classe de inicialização com `@EnableEurekaServer`
```java
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
```

- especificar a porta e as configurações referente ao discovery `discovery-server.yml` 
```java
server:
  port: ${SERVER_PORT:8761}

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## User-Greetings-Service
Responsável por receber o nome do usuário e buscar a saudação no serviço greetings-service.

#### Configurações
- anotar a classe de inicialização com `@EnableDiscoveryClient`, `@EnableCircuitBreaker` e `@EnableCircuitBreaker`
```java
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class UserGreetingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGreetingsServiceApplication.class, args);
	}

}
```

- especificar a porta e as configurações referente ao discovery e ao circuit break `user-greetings-service.yml` 
```java
server:
  port: ${SERVER_PORT:8080}

eureka:
  instance:
    leaseRenewalIntervalInSeconds: 1
    leaseExpirationDurationInSeconds: 2
  client:
    serviceUrl:
      defaultZone: ${DISCOVERY_URI:http://localhost:8761/eureka}
    healthcheck:
      enabled: true

hystrix:
  command:
    default:
      circuitBreaker:
        requestVolumeThreshold: 5
        sleepWindowInMilliseconds: 5000

greetings-service:
  ribbon:
    eureka:
      enabled: true
```

## Greetings-Service
Responsável por gerar a saudação de acordo com o horário.

#### Configurações
- anotar a classe de inicialização com `@EnableDiscoveryClient`
```java
@EnableDiscoveryClient
@SpringBootApplication
public class GreetingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingsServiceApplication.class, args);
	}

}
```

---

## Execução dos Serviços

- Para executar os serviços é necessário acessar via terminal cada um dos serviços e rodar o seguinte comando Maven:
  - `mvn spring-boot:run`
  
- Para testar a execução, após inicializar todos os serviços:
  - http://localhost:8080
