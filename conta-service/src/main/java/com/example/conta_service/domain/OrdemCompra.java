package com.example.conta_service.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class OrdemCompra {

    private String id;
    private String cpfCliente;
    private BigDecimal valor;

    // construtor vazio (obrigatório para o spring transformar em json depois)
    public OrdemCompra() {
    }

    // construtor com dados
    public OrdemCompra(String cpfCliente, BigDecimal valor) {
        this.id = UUID.randomUUID().toString(); // gera um ID único aleatório
        this.cpfCliente = cpfCliente;
        this.valor = valor;
    }

    // getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}