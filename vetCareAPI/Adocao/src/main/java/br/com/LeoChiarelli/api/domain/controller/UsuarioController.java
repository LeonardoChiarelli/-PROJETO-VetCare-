package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.api.domain.model.Usuario;
import br.com.LeoChiarelli.api.domain.service.UsuarioService;
import br.com.LeoChiarelli.api.general.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adocoes")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO dto){
        service.cadastrar(dto);

        return ResponseEntity.ok("Usuário cadastrato com sucesso, tente fazer o login");
    }

    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO dto){
        try {
            var authenticationManager = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
            var authentication = manager.authenticate(authenticationManager);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(tokenJWT);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }
}
