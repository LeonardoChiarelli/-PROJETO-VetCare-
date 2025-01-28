package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.validators;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.vetCareAPI.general.exception.ValidacaoException;

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
