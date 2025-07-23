package com.cairo.Livraria.dtos;

import java.util.List;

import com.cairo.Livraria.models.cliente.Aluguel;
import com.cairo.Livraria.models.cliente.Cliente;
import com.cairo.Livraria.models.cliente.Conta;
import com.cairo.Livraria.models.cliente.Dados;


public record ClienteResponseDTO(Long id, String nome, Boolean devendo, Conta conta, Dados dados, List<Aluguel> livrosAlugados) {
    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getDevendo(), cliente.getConta(), cliente.getDados(), cliente.getLivrosAlugados());
    }
}
