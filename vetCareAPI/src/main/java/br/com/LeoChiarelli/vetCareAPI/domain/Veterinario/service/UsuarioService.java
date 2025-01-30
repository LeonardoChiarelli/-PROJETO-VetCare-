package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.MudarRoleUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IPerfilRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IUsuarioRepository;
import br.com.LeoChiarelli.vetCareAPI.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private IPerfilRepository perfilRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void mudarRole(MudarRoleUsuarioDTO dto) {

        var usuario = repository.findById(dto.idUsuario()).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
        var perfil = perfilRepository.findById(dto.idPerfil()).orElseThrow(() -> new ValidacaoException("Perfil não encontrado"));

        usuario.getPerfis().clear();
        usuario.getPerfis().add(perfil);

        repository.save(usuario);

    }
}
