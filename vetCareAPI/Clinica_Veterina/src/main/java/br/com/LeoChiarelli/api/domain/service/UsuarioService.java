package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.model.Perfil;
import br.com.LeoChiarelli.api.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.api.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.api.domain.repository.IUsuarioRepository;
import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.model.Usuario;
import br.com.LeoChiarelli.api.domain.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
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
}
