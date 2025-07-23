package com.cairo.Livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cairo.Livraria.models.livro.Livro;
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByNomeAndAutor(String nome, String autor);
}
