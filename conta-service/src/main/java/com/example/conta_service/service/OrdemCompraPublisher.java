package com.example.conta_service.service;

import com.example.conta_service.config.RabbitMQConfig;
import com.example.conta_service.domain.OrdemCompra;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service // avisa o spring que esta é uma classe de regra de negócio
public class OrdemCompraPublisher {

    private final RabbitTemplate rabbitTemplate;

    // injeção de dependência pelo construtor
    public OrdemCompraPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarOrdem(OrdemCompra ordem) {
        // envia para o exchange e a rota na configuração
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ORDEM_COMPRA,
                RabbitMQConfig.ROUTING_KEY_ORDEM_COMPRA,
                ordem);
        System.out.println("[Conta-Service] Ordem de Compra enviada para a fila: " + ordem.getId());
    }
}