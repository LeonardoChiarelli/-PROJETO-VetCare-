package br.com.LeoChiarelli.api.general.infra.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message){
        super(message);
    }
}
