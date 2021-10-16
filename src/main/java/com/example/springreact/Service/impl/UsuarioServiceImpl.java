package com.example.springreact.Service.impl;

import com.example.springreact.Exceptions.ErroAutenticacao;
import com.example.springreact.Exceptions.RegraNegocioException;
import com.example.springreact.Service.UsuarioService;
import com.example.springreact.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springreact.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
    Optional<Usuario> usuario=repository.findByEmail(email);
        if(!usuario.isPresent()) {
        throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }
        if(!usuario.get().getSenha().equals(senha)){
            throw new ErroAutenticacao("Senha invalida");
        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
    boolean existe = repository.existsByEmail(email);
    if(existe){
        throw  new RegraNegocioException("Já existe um usuario cadastrado com esse email");
    }
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }
}
