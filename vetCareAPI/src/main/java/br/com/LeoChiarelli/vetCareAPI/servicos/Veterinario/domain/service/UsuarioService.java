package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.service;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.MudarRoleUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Usuario;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IUsuarioRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private IPerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void cadastrar(@Valid CadastrarUsuarioDTO dto) {
        String senhaEncriptada = passwordEncoder.encode(dto.senha());

        repository.save(new Usuario(dto.nome(), dto.email(), senhaEncriptada));
    }
    
    public void mudarRole(MudarRoleUsuarioDTO dto) {

        var usuario = repository.findById(dto.idUsuario()).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
        var perfil = perfilRepository.findById(dto.idPerfil()).orElseThrow(() -> new ValidacaoException("Perfil não encontrado"));

        usuario.getPerfis().clear();
        usuario.getPerfis().add(perfil);

        repository.save(usuario);

    }
}
