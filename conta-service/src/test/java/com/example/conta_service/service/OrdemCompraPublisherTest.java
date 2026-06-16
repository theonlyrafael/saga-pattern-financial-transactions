package com.example.conta_service.service;

import com.example.conta_service.config.RabbitMQConfig;
import com.example.conta_service.domain.OrdemCompra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrdemCompraPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrdemCompraPublisher ordemCompraPublisher;

    @Test
    public void testPublishOrdemCompra() {
        // arrange (prepara dados)
        OrdemCompra ordem = new OrdemCompra("12345678900", new BigDecimal("1050.00"));

        // act (executa ação)
        ordemCompraPublisher.enviarOrdem(ordem);

        // assert (verifica resultado)
        verify(rabbitTemplate).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE_ORDEM_COMPRA),
                eq(RabbitMQConfig.ROUTING_KEY_ORDEM_COMPRA),
                eq(ordem));
    }
}