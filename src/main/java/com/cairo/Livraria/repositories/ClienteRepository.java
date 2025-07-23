package com.cairo.Livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cairo.Livraria.models.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByContaEmail(String email);
    boolean existsByContaUsername(String username);
    boolean existsByDadosTelefone(String telefone);
}
