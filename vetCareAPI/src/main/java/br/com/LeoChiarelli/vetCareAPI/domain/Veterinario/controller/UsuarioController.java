package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.MudarRoleUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Perfil;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Usuario;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IUsuarioRepository;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.UsuarioService;
import br.com.LeoChiarelli.vetCareAPI.general.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarUsuarioDTO dto) {
        String senhaEncriptada = passwordEncoder.encode(dto.senha());

        repository.save(new Usuario(dto.nome(), dto.email(), senhaEncriptada));

        return ResponseEntity.ok().body("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO dto) {
        try {
            var authenticationManager = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
            var authentication = manager.authenticate(authenticationManager);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(tokenJWT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }

    @PutMapping("/admin/role")
    @Transactional
    public void mudarRole(MudarRoleUsuarioDTO dto){
        service.mudarRole(dto);
    }
}
