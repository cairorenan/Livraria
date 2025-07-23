package com.cairo.Livraria.models.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class VerificarDupCliente {
    private boolean emailDuplicado;
    private boolean usernameDuplicado;
    private boolean telefoneDuplicado;

}
