package br.com.LeoChiarelli.Veterinario.general.infra.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message){
        super(message);
    }
}
