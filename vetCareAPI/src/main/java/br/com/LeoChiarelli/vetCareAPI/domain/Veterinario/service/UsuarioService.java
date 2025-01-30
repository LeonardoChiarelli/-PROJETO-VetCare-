package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.TokenJWTDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Usuario;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IUsuarioRepository;
import br.com.LeoChiarelli.vetCareAPI.general.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository repository;

    public void cadastrar(CadastrarUsuarioDTO dto) {
        String senhaEncriptada = passwordEncoder.encode(dto.senha());

        repository.save(new Usuario(dto.nome(), dto.email(), senhaEncriptada));
    }

    public ResponseEntity efetuarLogin(EfetuarLoginDTO dto) {
        try {
            var authenticationManager = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
            var authentication = manager.authenticate(authenticationManager);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
