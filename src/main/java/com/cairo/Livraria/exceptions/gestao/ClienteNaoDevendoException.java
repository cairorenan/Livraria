package com.cairo.Livraria.exceptions.gestao;

public class ClienteNaoDevendoException extends GestaoExceptions{
    public ClienteNaoDevendoException(){
        super("Cliente não Deve Nenhum Livro");
    }
}
