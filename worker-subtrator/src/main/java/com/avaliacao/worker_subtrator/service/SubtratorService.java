package com.avaliacao.worker_subtrator.service;

import com.avaliacao.worker_subtrator.dto.NumerosDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SubtratorService {

    @RabbitListener(queues = "${queue.nome}") //
    public int realizarSubtracao(NumerosDTO numeros) {
        System.out.println("Recebido: " + numeros.numeroA + " - " + numeros.numeroB);

        int resultado = numeros.numeroA - numeros.numeroB;

        return resultado;
    }
}
