package com.example.springreact.api.resource;

import com.example.springreact.Exceptions.ErroAutenticacao;
import com.example.springreact.Exceptions.RegraNegocioException;
import com.example.springreact.Service.UsuarioService;
import com.example.springreact.api.dto.UsuarioDTO;
import com.example.springreact.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService service;

    /*@Autowired
    public UsuarioResource(UsuarioService service) {
        this.service = service;
    }*/

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
       try{
           Usuario usuarioAutenticado= service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
       } catch (ErroAutenticacao e){
          return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto){
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try{
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
