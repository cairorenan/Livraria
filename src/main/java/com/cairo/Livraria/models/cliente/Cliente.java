package com.cairo.Livraria.models.cliente;

import java.util.ArrayList;
import java.util.List;

import com.cairo.Livraria.dtos.ClienteRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ClienteDB")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean devendo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    @JsonBackReference(value = "cliente-conta")
    private Conta conta;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_id")
    @JsonBackReference(value = "dados-conta")
    private Dados dados;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Aluguel> livrosAlugados = new ArrayList<>();

    public Cliente(ClienteRequestDTO data){
        this.nome = data.nome();
        this.conta = data.conta();
        this.dados = data.dados();
        this.devendo = false;
    }
}
