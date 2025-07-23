package com.cairo.Livraria.exceptions.gestao;

public class UnidadesInsuficienteException extends GestaoExceptions {
    public UnidadesInsuficienteException(int unidades_requerida, int unidadades_estoque){
        super("Unidades Insurficientes, Unidades de Livros: "+unidadades_estoque+" Unidades Requeridas: "+unidades_requerida);
    }
}
