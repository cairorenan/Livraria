package com.cairo.Livraria.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairo.Livraria.dtos.ClienteRequestDTO;
import com.cairo.Livraria.dtos.ClienteResponseDTO;
import com.cairo.Livraria.exceptions.cliente.EmailDuplicadoException;
import com.cairo.Livraria.exceptions.cliente.UsernameDuplicadoException;
import com.cairo.Livraria.exceptions.gestao.ClienteNaoEncontradoException;
import com.cairo.Livraria.models.cliente.Cliente;
import com.cairo.Livraria.models.cliente.Conta;
import com.cairo.Livraria.models.cliente.Dados;
import com.cairo.Livraria.models.cliente.VerificarDupCliente;
import com.cairo.Livraria.exceptions.cliente.TelefoneDuplicadoException;
import com.cairo.Livraria.repositories.ClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public void cadastrarCLiente(ClienteRequestDTO data){
        String email = data.conta().getEmail();
        String username = data.conta().getUsername();
        String telefone = data.dados().getTelefone();
        VerificarDupCliente verificacoes = verificar(email, username, telefone);
        if (verificacoes.isEmailDuplicado()) throw new EmailDuplicadoException();
        if (verificacoes.isUsernameDuplicado()) throw new UsernameDuplicadoException();
        if (verificacoes.isTelefoneDuplicado()) throw new TelefoneDuplicadoException();
        Cliente novoCliente = new Cliente(data);
        clienteRepository.save(novoCliente);
    }

    public List<ClienteResponseDTO> listarClientes(){
        List<ClienteResponseDTO> listadeClientes = clienteRepository.findAll().stream().map(ClienteResponseDTO::new).toList();
        return listadeClientes;
    }

    public Cliente buscarCliente(Long cliente_id){
        Optional<Cliente> clienteOpt = clienteRepository.findById(cliente_id);
        if(clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            return cliente;
        }else throw new ClienteNaoEncontradoException();
    }

    @Transactional
    public void deletarCliente(Long cliente_id){
        Optional <Cliente> clienteOpt = clienteRepository.findById(cliente_id);
        if(clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            Conta conta = cliente.getConta();
            Dados dados = cliente.getDados();
            cliente.setConta(null);
            cliente.setDados(null);
            entityManager.remove(entityManager.contains(conta) ? conta : entityManager.merge(conta));
            entityManager.remove(entityManager.contains(dados) ? dados : entityManager.merge(dados));
            clienteRepository.delete(cliente);
        }else throw new ClienteNaoEncontradoException();
    }

    public void editarCliente(ClienteRequestDTO data, Long cliente_id){
        Optional<Cliente> clienteOpt = clienteRepository.findById(cliente_id);
        if(clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            Conta conta = cliente.getConta();
            Dados dados = cliente.getDados();
            String email = data.conta().getEmail();
            String username = data.conta().getUsername();
            String telefone = data.dados().getTelefone();
            VerificarDupCliente verificacoes = verificar(email, username, telefone);
            if (verificacoes.isEmailDuplicado()) throw new EmailDuplicadoException();
            if (verificacoes.isUsernameDuplicado()) throw new UsernameDuplicadoException();
            if (verificacoes.isTelefoneDuplicado()) throw new TelefoneDuplicadoException();
            conta.setEmail(email);
            conta.setUsername(username);
            conta.setPassword(data.conta().getPassword());
            dados.setCpf(data.dados().getCpf());
            dados.setTelefone(telefone);
            dados.setEndereco(data.dados().getEndereco());
            cliente.setNome(data.nome());
            clienteRepository.save(cliente);
        }else throw new ClienteNaoEncontradoException();
    }

    private VerificarDupCliente verificar(String Nemail, String Nusername, String Ntelefone){
        boolean existeEmail = clienteRepository.existsByContaEmail(Nemail);
        boolean existeUsername = clienteRepository.existsByContaUsername(Nusername);
        boolean existeTelefone = clienteRepository.existsByDadosTelefone(Ntelefone);
        return new VerificarDupCliente(existeEmail, existeUsername, existeTelefone);
    }


}
