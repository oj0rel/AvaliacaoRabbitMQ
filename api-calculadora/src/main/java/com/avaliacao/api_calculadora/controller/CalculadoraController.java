package com.avaliacao.api_calculadora.controller;

import com.avaliacao.api_calculadora.dto.NumerosDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.nome}")
    private String nomeFila;

    @PostMapping("/subtrair")
    public String subtrair(@RequestBody NumerosDTO numeros) {
        Object resposta = rabbitTemplate.convertSendAndReceive(nomeFila, numeros);

        return "O resultado é: " + resposta;
    }
}
