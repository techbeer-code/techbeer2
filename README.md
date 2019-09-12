# Alelo Tech & Beer - Microsserviços

Microsserviços utilizados na demostração apresentada durante o Techbeer.

<div align="center">
    <img src="/microservicos.png" /> 
</div>

## Config-Server
Responsável pela gestão das configurações dos demais serviços.

## Discovery-Server
Responsável pelo registro das instancias de serviços em execução.

## User-Greetings-Service
Responsável por receber o nome do usuário e buscar a saudação no serviço greetings-service.

## Greetings-Service
Responsável por gerar a saudação de acordo com o horário.

---

Para testar a execução, após inicializar todos os serviços acesse: http://localhost:8080
