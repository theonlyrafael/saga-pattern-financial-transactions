package com.example.conta_service.repository;

import com.example.conta_service.domain.OrdemCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// avisa ao spring que essa interface é um repositório, ou seja, uma classe que vai acessar o banco de dados
public interface OrdemCompraRepository extends JpaRepository<OrdemCompra, UUID> {

}
