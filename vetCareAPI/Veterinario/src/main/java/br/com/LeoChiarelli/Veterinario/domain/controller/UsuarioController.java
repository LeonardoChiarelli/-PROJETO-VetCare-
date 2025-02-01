package br.com.LeoChiarelli.Veterinario.domain.controller;

import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.MudarRoleUsuarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Usuario;
import br.com.LeoChiarelli.Veterinario.domain.service.UsuarioService;
import br.com.LeoChiarelli.Veterinario.general.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinica")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastrarUsuarioDTO dto) {
        service.cadastrar(dto);

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

    @PutMapping("/admin/usuarios/role")
    @Transactional
    public void mudarRole(MudarRoleUsuarioDTO dto){
        service.mudarRole(dto);
    }
}
