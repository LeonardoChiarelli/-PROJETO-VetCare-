package br.com.LeoChiarelli.api.general.infra.validators;

import br.com.LeoChiarelli.api.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
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
