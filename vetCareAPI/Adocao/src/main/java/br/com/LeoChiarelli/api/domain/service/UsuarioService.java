package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.dto.EfetuarLoginDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public void cadastrar(@Valid CadastrarUsuarioDTO dto) {
    }

    public void login(@Valid EfetuarLoginDTO dto) {

    }
}
