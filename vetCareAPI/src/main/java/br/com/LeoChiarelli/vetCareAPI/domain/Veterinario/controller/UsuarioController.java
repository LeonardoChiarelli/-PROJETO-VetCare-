package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.TokenJWTDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Usuario;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IUsuarioRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.UsuarioService;
import br.com.LeoChiarelli.vetCareAPI.general.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioRepository repository;


    @PostMapping("/cadastrar")
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarUsuarioDTO dto) {
        String senhaEncriptada = passwordEncoder.encode(dto.senha());

        repository.save(new Usuario(dto.nome(), dto.email(), senhaEncriptada));
    }

    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO dto) {
        var authenticationManager = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationManager);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }
}
