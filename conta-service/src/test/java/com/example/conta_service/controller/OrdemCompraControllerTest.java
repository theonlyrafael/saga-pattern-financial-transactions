package com.example.conta_service.controller;

import com.example.conta_service.domain.OrdemCompra;
import com.example.conta_service.service.OrdemCompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
    private MockMvc mockMvc;

    // atualizado para espelhar a nova dependência real do controller
    @MockitoBean
    private OrdemCompraService service;

    @Test
    public void deveReceberRequisicaoHttpEProcessarOrdemComSucesso() throws Exception {
        String jsonRequisicao = """
                {
                    "cpfCliente": "12345678900",
                    "valor": 1050.00
                }
                """;

        mockMvc.perform(post("/api/ordens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequisicao))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ordem de compra recebida")));

        // verifica se o controlador acionou o serviço exatamente uma vez
        verify(service).processarNovaOrdem(any(OrdemCompra.class));
    }
}



/*
 * package com.example.conta_service.controller;
 * 
 * import com.example.conta_service.service.OrdemCompraPublisher;
 * import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired;
 * // import corrigido para o Spring Boot 4.x
 * import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
 * import org.springframework.test.context.bean.override.mockito.MockitoBean;
 * import org.springframework.http.MediaType;
 * import org.springframework.test.web.servlet.MockMvc;
 * 
 * import static org.mockito.ArgumentMatchers.any;
 * import static org.mockito.Mockito.verify;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 * import static org.hamcrest.Matchers.containsString;
 * 
 * @WebMvcTest(OrdemCompraController.class)
 * public class OrdemCompraControllerTest {
 * 
 * // a ferramenta que simula o Postman/Navegador
 * 
 * @Autowired
 * private MockMvc mockMvc;
 * 
 * // cria um dublê do serviço para não enviar nada real pro RabbitMQ
 * 
 * @MockitoBean
 * private OrdemCompraPublisher publisher;
 * 
 * // arrange (preparação): cria um json de mentira simulando a requisição do
 * // usuário
 * 
 * @Test
 * public void deveReceberRequisicaoHttpEProcessarOrdemComSucesso() throws
 * Exception {
 * String jsonRequisicao = """
 * {
 * "cpfCliente": "12345678900",
 * "valor": 1050.00
 * }
 * """;
 * 
 * // act & assert (ação e verificação)
 * mockMvc.perform(post("/api/ordens") // dispara um post para a nossa rota
 * .contentType(MediaType.APPLICATION_JSON) // avisa que o json está sendo
 * enviado
 * .content(jsonRequisicao)) // coloca o json no corpo da requisição
 * 
 * // verificações:
 * .andExpect(status().isOk())
 * .andExpect(content().string(containsString("Ordem de compra recebida")));
 * 
 * // garante que o Controller chamou o serviço repassando o objeto
 * verify(publisher).enviarOrdem(any());
 * }
 * }
 */