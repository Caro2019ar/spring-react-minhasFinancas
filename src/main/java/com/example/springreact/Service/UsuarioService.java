package com.example.springreact.Service;

import com.example.springreact.model.entity.Usuario;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email);

}
