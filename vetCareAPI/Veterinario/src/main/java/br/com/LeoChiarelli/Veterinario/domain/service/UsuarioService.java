package br.com.LeoChiarelli.Veterinario.domain.service;

import br.com.LeoChiarelli.Veterinario.domain.model.Perfil;
import br.com.LeoChiarelli.Veterinario.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.IUsuarioRepository;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.MudarRoleUsuarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Usuario;
import br.com.LeoChiarelli.Veterinario.domain.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ITutorRepository tutorRepository;

    @Autowired
    private IVeterinarioRepository veterinarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void cadastrar(@Valid CadastrarUsuarioDTO dto) {

        var existeTutor = tutorRepository.existsByEmail(dto.email());
        var existeVet = veterinarioRepository.existsByEmail(dto.email());
        String senhaEncriptada = passwordEncoder.encode(dto.senha());

        if(existeTutor) {
            var tutor = tutorRepository.getReferencesByEmail(dto.email());


            Perfil perfil = perfilRepository.getReferenceById(2L);
            repository.save(new Usuario(tutor.getNome(), dto.email(), perfil, senhaEncriptada));
        }
        if(existeVet) {
            var veterinario = veterinarioRepository.getReferencesByEmail(dto.email());
            Perfil perfil = perfilRepository.getReferenceById(3L);
            repository.save(new Usuario(veterinario.getNome(), dto.email(), perfil, senhaEncriptada));
        }

    }

    public void mudarRole(MudarRoleUsuarioDTO dto) {

        var usuario = repository.findById(dto.idUsuario()).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
        var perfil = perfilRepository.findById(dto.idPerfil()).orElseThrow(() -> new ValidacaoException("Perfil não encontrado"));

        usuario.getPerfis().clear();
        usuario.getPerfis().add(perfil);

        repository.save(usuario);

    }
}
