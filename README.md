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
- especificar onde o repostório GIT com as configurações está localizado no arquivo `application.yml` 
```java
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

## Execução
Para testar a execução, após inicializar todos os serviços, acessar a URL: http://localhost:8080
