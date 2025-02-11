package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.CadastrarUsuarioDTO;
import br.com.LeoChiarelli.api.domain.dto.EfetuarLoginDTO;
import br.com.LeoChiarelli.api.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adocoes")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO dto){
        service.cadastrar(dto);

        return ResponseEntity.ok("Usuário cadastrato com sucesso, tente fazer o login");
    }

    @PostMapping("/login")
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO dto){
        service.login(dto);
    }
}
