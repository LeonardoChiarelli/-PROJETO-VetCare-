package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.api.domain.model.Perfil;
import br.com.LeoChiarelli.api.domain.model.Usuario;
import br.com.LeoChiarelli.api.domain.repository.IAbrigoRepository;
import br.com.LeoChiarelli.api.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.api.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.api.domain.repository.IUsuarioRepository;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IPerfilRepository perfilRepository;

    @Autowired
    private ITutorRepository tutorRepository;

    @Autowired
    private IAbrigoRepository abrigoRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void cadastrar(@Valid CadastrarUsuarioDTO dto) {
        var existeAbrigo = abrigoRepository.findByEmail(dto.email());
        var existeTutor = tutorRepository.findByEmail(dto.email());
        var senhaEncriptada = passwordEncoder.encode(dto.senha());

        if (existeAbrigo){
            var abrigo = abrigoRepository.getReferenceByEmail(dto.email());

            var perfil = buscarPerfil(1L);
            repository.save(new Usuario(abrigo.getNome(), dto.email(), perfil, senhaEncriptada));
        }

        if (existeTutor){
            var tutor = tutorRepository.getReferenceByEmail(dto.email());

            var perfil = buscarPerfil(2L);
            repository.save(new Usuario(tutor.getNome(), dto.email(), perfil, senhaEncriptada));
        }
    }

    public Perfil buscarPerfil(Long id){
        return perfilRepository.getReferenceById(id);
    }

}
