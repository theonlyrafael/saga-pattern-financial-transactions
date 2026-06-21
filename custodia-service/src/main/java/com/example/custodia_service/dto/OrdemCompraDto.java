package com.example.custodia_service.dto;

import java.math.BigDecimal;

public class OrdemCompraDto {
    private String cpfCliente;
    private BigDecimal valor;
    private String status;

    public OrdemCompraDto() {
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
