package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.general.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Antecedencia implements IValidation{
    @Override
    public void validar(CadastrarConsultaDTO dto) {

        var data = dto.dataHora();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, data).toMinutes();

        if (diferencaEmMinutos < 20) { throw new ValidacaoException("Consultas devem ser marcadas com 20 minutos de antecedência"); }
    }

    public static void validarTempo(AtualizarConsultaDTO dto){
        var data = dto.dataHora();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, data).toMinutes();

        if (diferencaEmMinutos < 20) { throw new ValidacaoException("Consultas devem ser marcadas com 20 minutos de antecedência"); }
    }
}
