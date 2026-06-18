package com.example.conta_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

// avisa o framework que esta classe deve virar uma tabela no banco de dados
@Entity
public class OrdemCompra {

    // define este campo como a chave primária da tabela
    @Id
    // manda o banco de dados gerar o uuid automaticamente na hora de salvar
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cpfCliente;
    private BigDecimal valor;
    private String status;

    // construtor vazio (obrigatório para o spring transformar em json e para o mapeamento do jpa)
    public OrdemCompra() {
    }

    // construtor com dados para ser instanciado na criação de novas ordens
    public OrdemCompra(String cpfCliente, BigDecimal valor) {
        this.cpfCliente = cpfCliente;
        this.valor = valor;
        this.status = "PENDENTE"; // toda ordem nasce com este status
    }

    // getters e setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



// ------ CÓDIGO ANTIGO ------

/* 
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
    */