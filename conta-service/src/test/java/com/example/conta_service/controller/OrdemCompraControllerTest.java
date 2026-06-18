package com.example.conta_service.controller;

import com.example.conta_service.service.OrdemCompraPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(OrdemCompraController.class)
public class OrdemCompraControllerTest {

    @Autowired
    private MockMvc mockMvc; // a ferramenta que simula o Postman/Navegador

    @MockBean
    private OrdemCompraPublisher publisher; // cria um dublê do serviço para não enviar nada real pro RabbitMQ

    @Test
    public void deveReceberRequisicaoHttpEProcessarOrdemComSucesso() throws Exception {
        // arrange (preparação): criamos um json de mentira simulando a requisição do usuário
        String jsonRequisicao = """
                {
                    "cpfCliente": "12345678900",
                    "valor": 1050.00
                }
                """;

        // act & assert (ação e verificação)
        mockMvc.perform(post("/api/ordens") // dispara um post para a nossa rota
                .contentType(MediaType.APPLICATION_JSON) // avisa que estamos mandando json
                .content(jsonRequisicao)) // coloca o json no corpo da requisição
                
                // verificações:
                .andExpect(status().isOk()) 
                .andExpect(content().string(containsString("Ordem de compra recebida"))); 

        // garante que o Controller chamou o serviço repassando o objeto
        verify(publisher).enviarOrdem(any());
    }
}