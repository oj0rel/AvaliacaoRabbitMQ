# 🧮 Calculadora Distribuída com Spring Boot e RabbitMQ (RPC)

Este repositório contém uma solução de microsserviços para realizar operações matemáticas (subtração) de forma assíncrona/distribuída utilizando o padrão **RPC (Remote Procedure Call)** sobre o protocolo AMQP.

O projeto demonstra a comunicação entre dois serviços distintos (API e Worker) trocando mensagens JSON através do RabbitMQ.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.8**
* **Spring AMQP (RabbitMQ)**
* **Maven**
* **Jackson** (Serialização JSON)

## 📂 Estrutura do Projeto

O repositório é dividido em dois microsserviços independentes:

* **`api-calculadora` (Producer):** API REST que recebe a requisição do usuário, envia os dados para a fila e aguarda a resposta.
* **`worker-subtrator` (Consumer):** Serviço que escuta a fila, processa o cálculo matemático e devolve o resultado para a API.

## ⚙️ Arquitetura (Fluxo RPC)

O sistema utiliza o método `convertSendAndReceive` do RabbitTemplate para simular uma chamada síncrona sobre uma arquitetura assíncrona:

1.  **Client** envia `POST /subtrair` com JSON.
2.  **API** converte o objeto para JSON e envia para a fila `queue.nome`.
3.  **Worker** consome a mensagem, realiza a subtração (`A - B`).
4.  **Worker** retorna o resultado automaticamente para uma fila temporária de resposta.
5.  **API** recebe o resultado e devolve HTTP 200 para o cliente.

## 🛠️ Como Executar

### Pré-requisitos
* RabbitMQ rodando na porta `5672` (Localmente ou via Docker).
* Java JDK 21+ instalado.

### Passo 1: Iniciar o RabbitMQ (Se usar Docker)
```bash
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
