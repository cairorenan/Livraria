package com.cairo.Livraria.dtos;

import com.cairo.Livraria.models.cliente.Conta;
import com.cairo.Livraria.models.cliente.Dados;

public record ClienteRequestDTO(String nome, Conta conta, Dados dados) {

}
