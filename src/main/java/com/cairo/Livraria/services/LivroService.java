package com.cairo.Livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairo.Livraria.dtos.LivroRequestDTO;
import com.cairo.Livraria.dtos.LivroResponseDTO;
import com.cairo.Livraria.exceptions.gestao.LivroNaoEncontradoException;
import com.cairo.Livraria.models.livro.Livro;
import com.cairo.Livraria.repositories.LivroRepository;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;  

    public void cadastrarLivro(LivroRequestDTO data){
        Optional<Livro> livroExiste = livroRepository.findByNomeAndAutor(data.nome(), data.autor());
        Livro novoLivro;
        if(livroExiste.isPresent()){
            Livro livroExistente = livroExiste.get();
            livroExistente.setUnidades(livroExistente.getUnidades() + data.unidades());
            novoLivro = livroExistente;
        }else{novoLivro = new Livro(data);}
        livroRepository.save(novoLivro);
    }

    public List<LivroResponseDTO> listarLivro(){
        List<LivroResponseDTO> listadeLivros = livroRepository.findAll().stream().map(LivroResponseDTO::new).toList();
        return listadeLivros;
    }

    public Livro buscarLivro(Long livro_id){
        Optional<Livro> livroOpt = livroRepository.findById(livro_id);
        if(livroOpt.isPresent()){
            Livro livro = livroOpt.get();
            return livro;
        }else throw new LivroNaoEncontradoException();
    }

    public void deletarLivro(Long id){
        livroRepository.findById(id).ifPresent(livroRepository::delete);
    }

    public void editarLivro(LivroRequestDTO data,Long livro_id){
        Optional <Livro> livroOpt = livroRepository.findById(livro_id);
        if(livroOpt.isPresent()){
            Livro livro = livroOpt.get();
            livro.setAutor(data.autor());
            livro.setNome(data.nome());
            livro.setImagem(data.imagem());
            livro.setUnidades(data.unidades());
            livroRepository.save(livro);
        }
    }

}
