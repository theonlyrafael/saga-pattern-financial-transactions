package com.example.conta_service.service;

import com.example.conta_service.domain.OrdemCompra;
import com.example.conta_service.repository.OrdemCompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// define a classe como o componente central de regras de negocio
@Service
public class OrdemCompraService {

    private final OrdemCompraRepository repository;
    private final OrdemCompraPublisher publisher;

    // injeção de dependência pelo construtor
    public OrdemCompraService(OrdemCompraRepository repository, OrdemCompraPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    // garante que as operações de banco de dados sejam atômicas, ou seja, ou todas
    // são executadas com sucesso ou nenhuma é aplicada
    @Transactional
    public OrdemCompra processarNovaOrdem(OrdemCompra ordem) {

        OrdemCompra ordemSalva = repository.save(ordem);
        publisher.enviarOrdem(ordemSalva);
        return ordemSalva;
    }
}