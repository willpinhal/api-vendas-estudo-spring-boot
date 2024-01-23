package com.github.willpinhal.apivendas.apivendas.exceptions;

public class SenhaInvalidException extends RuntimeException {

    public SenhaInvalidException(){
        super("Senha inválida");
    }
}
