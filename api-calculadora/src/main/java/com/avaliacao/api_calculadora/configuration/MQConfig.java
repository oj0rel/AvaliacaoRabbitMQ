package com.avaliacao.api_calculadora.configuration; // ATENÇÃO: Verifique se o pacote é api_calculadora ou worker_subtrator

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${queue.nome}")
    private String queueName;

    private Queue queue;

    private DirectExchange createDirectExchange() {
        return new DirectExchange("rmq-exchange-prova");
    }

    @PostConstruct
    private void Create() {
        this.queue = new Queue(queueName, true, false, false);

        DirectExchange directExchange = createDirectExchange();

        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE,
                directExchange.getName(), queue.getName(), null);

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }
}