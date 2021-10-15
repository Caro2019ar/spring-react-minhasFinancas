package com.example.springreact.Exceptions;

public class ErroAutenticacao extends RuntimeException{
    public ErroAutenticacao(String message) {
        super(message);
    }
}
