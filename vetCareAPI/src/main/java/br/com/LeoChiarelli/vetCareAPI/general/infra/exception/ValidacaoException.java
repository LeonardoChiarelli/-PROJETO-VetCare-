package br.com.LeoChiarelli.vetCareAPI.general.infra.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String message){
        super(message);
    }
}
