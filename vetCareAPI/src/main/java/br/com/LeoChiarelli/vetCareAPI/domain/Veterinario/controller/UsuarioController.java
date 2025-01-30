package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.controller;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.TokenJWTDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO dto){
        service.cadastrar(dto);

        return ResponseEntity.ok().body("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO dto){
        var tokenJWT = service.efetuarLogin(dto);

        return ResponseEntity.ok(tokenJWT);
    }
}
