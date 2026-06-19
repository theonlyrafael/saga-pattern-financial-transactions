package com.example.conta_service.service;

import com.example.conta_service.domain.OrdemCompra;
import com.example.conta_service.repository.OrdemCompraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdemCompraServiceTest {

    @Mock
    private OrdemCompraRepository repository; // cria o duble do banco de dados

    @Mock
    private OrdemCompraPublisher publisher; // cria o duble do mensageiro

    @InjectMocks
    private OrdemCompraService service; // injeta os dubles no servico real

    @Test
    public void deveProcessarOrdemSalvandoNoBancoEEnviandoParaFila() {
        // arrange (preparacao)
        OrdemCompra ordemEntrada = new OrdemCompra("12345678900", new BigDecimal("1050.00"));
        
        OrdemCompra ordemSalva = new OrdemCompra("12345678900", new BigDecimal("1050.00"));
        ordemSalva.setId(UUID.randomUUID()); // simula a acao do banco de dados gerando o id

        // ensina o duble do banco a retornar a ordem com id quando o metodo save for chamado
        when(repository.save(any(OrdemCompra.class))).thenReturn(ordemSalva);

        // act (acao)
        OrdemCompra resultado = service.processarNovaOrdem(ordemEntrada);

        // assert (verificacao)
        assertNotNull(resultado.getId()); // garante que o servico retornou o objeto processado pelo banco
        assertEquals("PENDENTE", resultado.getStatus());
        
        // verifica se as duas ferramentas foram acionadas na ordem correta e com os dados corretos
        verify(repository).save(ordemEntrada);
        verify(publisher).enviarOrdem(ordemSalva);
    }
}