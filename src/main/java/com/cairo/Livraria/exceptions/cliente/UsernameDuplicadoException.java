package com.cairo.Livraria.exceptions.cliente;

public class UsernameDuplicadoException extends ClienteExceptions{
    public UsernameDuplicadoException(){
        super("Username já está em uso");
    }
}
