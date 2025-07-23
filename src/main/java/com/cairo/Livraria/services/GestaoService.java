package com.cairo.Livraria.services;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairo.Livraria.exceptions.gestao.ClienteNaoDevendoException;
import com.cairo.Livraria.exceptions.gestao.ClienteNaoEncontradoException;
import com.cairo.Livraria.exceptions.gestao.LivroNaoEncontradoException;
import com.cairo.Livraria.exceptions.gestao.UnidadesInsuficienteException;
import com.cairo.Livraria.models.cliente.Aluguel;
import com.cairo.Livraria.models.cliente.Cliente;
import com.cairo.Livraria.models.livro.Livro;
import com.cairo.Livraria.repositories.ClienteRepository;
import com.cairo.Livraria.repositories.LivroRepository;

@Service
public class GestaoService {
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    ClienteRepository clienteRepository;


    public void alugarLivro(Long cliente_id, Long livro_id, int unidades){
        Optional<Cliente> clienteOpt = clienteRepository.findById(cliente_id);
        Optional<Livro> livroOpt = livroRepository.findById(livro_id);
        if(clienteOpt.isPresent() && livroOpt.isPresent()){
            Livro livro = livroOpt.get();
            Cliente cliente = clienteOpt.get();
            if(livro.getUnidades()>=unidades){
                Aluguel livrojaexiste = cliente.getLivrosAlugados().stream().filter(l -> l.getNome().equals(livro.getNome()) && l.getAutor().equals(livro.getAutor())).findFirst().orElse(null);
                if(livrojaexiste!=null){
                    livrojaexiste.setUnidades(livrojaexiste.getUnidades()+unidades);
                    livro.setUnidades(livro.getUnidades()-unidades);
                }else{
                    Aluguel livrodocliente = new Aluguel();
                    livrodocliente.setCliente(cliente);
                    livrodocliente.setAutor(livro.getAutor());
                    livrodocliente.setNome(livro.getNome());
                    livrodocliente.setImagem(livro.getImagem());
                    livrodocliente.setUnidades(unidades);
                    livro.setUnidades(livro.getUnidades()-unidades);
                    livrodocliente.setDatadoAluguel(LocalDate.now());
                    cliente.getLivrosAlugados().add(livrodocliente);
                }
                cliente.setDevendo(true);
                livroRepository.save(livro);
                clienteRepository.save(cliente);
                if(livro.getUnidades()==0){livroRepository.delete(livro);}
            }else throw new UnidadesInsuficienteException(livro.getUnidades(), unidades);
        }else{
            if (clienteOpt.isEmpty()){throw new ClienteNaoEncontradoException();}
            if (livroOpt.isEmpty()){throw new LivroNaoEncontradoException();}
        }
    }

    public void devolverLivro(Long cliente_id, Long livro_id, int unidades){
        Optional<Cliente> clienteOpt = clienteRepository.findById(cliente_id);
        if(clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            if(cliente.getDevendo()==true){
                Aluguel livrodoCliente = cliente.getLivrosAlugados().stream().filter(l -> l.getId().equals(livro_id)).findFirst().orElse(null);
                if(livrodoCliente!=null){
                    Optional<Livro> livroOpt = livroRepository.findByNomeAndAutor(livrodoCliente.getNome(), livrodoCliente.getAutor());
                    if(livrodoCliente.getUnidades()>=unidades){
                        Livro livro = null;
                        if(livroOpt.isPresent()){
                            livro = livroOpt.get();
                            livro.setUnidades(livro.getUnidades()+unidades);
                            livrodoCliente.setUnidades(livrodoCliente.getUnidades()-unidades);
                        }else{
                            livro = new Livro();
                            livro.setAutor(livrodoCliente.getAutor());
                            livro.setNome(livrodoCliente.getNome());
                            livro.setImagem(livrodoCliente.getImagem());
                            livro.setUnidades(unidades);
                            livrodoCliente.setUnidades(livrodoCliente.getUnidades()-unidades);
                        }
                        if(livrodoCliente.getUnidades()==0){cliente.getLivrosAlugados().remove(livrodoCliente);}
                        if(cliente.getLivrosAlugados().isEmpty()){cliente.setDevendo(false);}
                        livroRepository.save(livro);
                        clienteRepository.save(cliente);
                    }else throw new UnidadesInsuficienteException(livrodoCliente.getUnidades(), unidades);
                }else throw new LivroNaoEncontradoException();
            }else throw new ClienteNaoDevendoException();
        }else throw new ClienteNaoEncontradoException();
    }
}
