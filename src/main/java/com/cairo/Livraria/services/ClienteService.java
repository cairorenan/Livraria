package com.cairo.Livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairo.Livraria.dtos.ClienteRequestDTO;
import com.cairo.Livraria.dtos.ClienteResponseDTO;
import com.cairo.Livraria.exceptions.cliente.EmailDuplicadoException;
import com.cairo.Livraria.exceptions.cliente.UsernameDuplicadoException;
import com.cairo.Livraria.models.cliente.Cliente;
import com.cairo.Livraria.models.cliente.VerificarDupCliente;
import com.cairo.Livraria.exceptions.cliente.TelefoneDuplicadoException;
import com.cairo.Livraria.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

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

    private VerificarDupCliente verificar(String Nemail, String Nusername, String Ntelefone){
        boolean existeEmail = clienteRepository.existsByContaEmail(Nemail);
        boolean existeUsername = clienteRepository.existsByContaUsername(Nusername);
        boolean existeTelefone = clienteRepository.existsByDadosTelefone(Ntelefone);
        return new VerificarDupCliente(existeEmail, existeUsername, existeTelefone);
    }


}
