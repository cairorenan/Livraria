package com.cairo.Livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cairo.Livraria.dtos.ClienteRequestDTO;
import com.cairo.Livraria.dtos.ClienteResponseDTO;
import com.cairo.Livraria.dtos.LivroRequestDTO;
import com.cairo.Livraria.dtos.LivroResponseDTO;
import com.cairo.Livraria.services.ClienteService;
import com.cairo.Livraria.services.GestaoService;
import com.cairo.Livraria.services.LivroService;

@RestController
@RequestMapping("/")
public class LivrariaController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LivroService livroService;
    @Autowired
    private GestaoService gestaoService;

    @PostMapping("/cliente")
    public void cadastrarCliente(@RequestBody ClienteRequestDTO data){
        clienteService.cadastrarCLiente(data);
    }

    @GetMapping("/cliente")
    public List<ClienteResponseDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/livro")
    public void cadastrarLivro(@RequestBody LivroRequestDTO data){
        livroService.cadastrarLivro(data);
    }

    @GetMapping("/livro")
    public List<LivroResponseDTO> listarLivros(){
        return livroService.listarLivro();
    }

    @PostMapping("/alugar/{id_cliente}/{id_livro}/{unidades}")
    public void alugarLivro(@PathVariable Long id_cliente, @PathVariable Long id_livro, @PathVariable int unidades){
        gestaoService.alugarLivro(id_cliente, id_livro, unidades);
    }

    @PostMapping("/devolver/{id_cliente}/{id_livro}/{unidades}")
    public void devolverLivro(@PathVariable Long id_cliente, @PathVariable Long id_livro, @PathVariable int unidades){
        gestaoService.devolverLivro(id_cliente, id_livro, unidades);
    }
}
