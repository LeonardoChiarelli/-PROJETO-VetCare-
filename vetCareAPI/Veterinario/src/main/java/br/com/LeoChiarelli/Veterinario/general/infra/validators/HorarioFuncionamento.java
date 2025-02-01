package br.com.LeoChiarelli.Veterinario.general.infra.validators;

import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class HorarioFuncionamento implements IValidation{

    @Override
    public void validar(CadastrarConsultaDTO dto) {
        var data = dto.dataHora();

        var antesDaAbertura = data.getHour() < 6;
        var depoisDoFechamento = data.getHour() > 21;

        if (antesDaAbertura || depoisDoFechamento) { throw new ValidacaoException("Consulta fora do horário de funcionamento da Clínica"); }
    }

    public static void validarTempo(AtualizarConsultaDTO dto){
        var data = dto.dataHora();

        var antesDaAbertura = data.getHour() < 6;
        var depoisDoFechamento = data.getHour() > 21;

        if (antesDaAbertura || depoisDoFechamento) { throw new ValidacaoException("Consulta fora do horário de funcionamento da Clínica"); }
    }
}
