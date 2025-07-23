package com.cairo.Livraria.exceptions.cliente;

public class TelefoneDuplicadoException extends ClienteExceptions{
    public TelefoneDuplicadoException(){
        super("Telefone já está em uso");
    }
}
