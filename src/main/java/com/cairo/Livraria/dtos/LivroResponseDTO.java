package com.cairo.Livraria.dtos;

import com.cairo.Livraria.models.livro.Livro;

public record LivroResponseDTO(Long id, String nome, String autor, String imagem, int unidades) {
    public LivroResponseDTO(Livro livro){
        this(livro.getId(),livro.getNome(), livro.getAutor(), livro.getImagem(), livro.getUnidades());
    }
}
