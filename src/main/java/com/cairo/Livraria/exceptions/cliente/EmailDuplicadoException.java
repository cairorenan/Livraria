package com.cairo.Livraria.exceptions.cliente;

public class EmailDuplicadoException extends ClienteExceptions{
    public EmailDuplicadoException(){
        super("Email já está em uso");
    }
}
