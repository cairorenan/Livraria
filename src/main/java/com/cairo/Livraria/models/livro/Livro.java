package com.cairo.Livraria.models.livro;

import com.cairo.Livraria.dtos.LivroRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "LivroDB")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private String imagem;
    private int unidades;

    public Livro(LivroRequestDTO data){
        this.nome = data.nome();
        this.autor = data.autor();
        this.imagem = data.imagem();
        this.unidades = data.unidades();
    }

    public Livro(Livro outro){
        this.id = null;
        this.nome = outro.getNome();
        this.autor = outro.getAutor();
        this.imagem = outro.getImagem();
        this.unidades = outro.getUnidades();
    }

}
