package com.example.custodia_service.amqp;

import com.example.custodia_service.domain.AtivoCustodia;
import com.example.custodia_service.dto.OrdemCompraDto;
import com.example.custodia_service.repository.AtivoCustodiaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustodiaListenerTest {

    @Mock
    private AtivoCustodiaRepository repository;

    @InjectMocks
    private CustodiaListener listener;

    @Test
    void deveProcessarOrdemESalvarCustodiaComSucesso() {
        OrdemCompraDto dto = new OrdemCompraDto();
        dto.setCpfCliente("12345678900");
        dto.setValor(new BigDecimal("1500.50"));

        // executa o método alvo simulando que a mensagem chegou
        listener.processarOrdem(dto);

        // captura o objeto exato que foi repassado para o método save do repositório
        ArgumentCaptor<AtivoCustodia> captor = ArgumentCaptor.forClass(AtivoCustodia.class);
        verify(repository).save(captor.capture());

        AtivoCustodia ativoSalvo = captor.getValue();

        assertEquals("12345678900", ativoSalvo.getCpfCliente());
        assertEquals(new BigDecimal("1500.50"), ativoSalvo.getValor());
        assertEquals("CUSTODIADO", ativoSalvo.getStatusCustodia());
    }
}