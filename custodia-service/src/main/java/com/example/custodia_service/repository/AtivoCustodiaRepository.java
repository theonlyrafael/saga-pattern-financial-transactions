package com.example.custodia_service.repository;

import com.example.custodia_service.domain.AtivoCustodia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtivoCustodiaRepository extends JpaRepository<AtivoCustodia, UUID> {

}