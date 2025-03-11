package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.model.Usuario;
import br.com.LeoChiarelli.api.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.api.domain.repository.IUsuarioRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private IPerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrar(@Valid CadastrarUsuarioDTO dto) {

        var usuarioExistente = repository.existsByEmail(dto.email());
        if (usuarioExistente) { throw new ValidacaoException("Email já está em uso, tente novamente"); }

        var senhaEncriptada = passwordEncoder.encode(dto.senha());
        var perfil = perfilRepository.findByNome("ROLE_COMPRADOR").orElseThrow(() -> new ValidacaoException("Perfil não econtrado"));
        repository.save(new Usuario(dto.email(), dto.nome(), List.of(perfil), senhaEncriptada));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }
}
