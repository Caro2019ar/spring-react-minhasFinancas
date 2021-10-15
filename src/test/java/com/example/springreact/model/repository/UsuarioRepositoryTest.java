package com.example.springreact.model.repository;

import com.example.springreact.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springreact.repository.UsuarioRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void verificarExistenciaDeEmail(){
        Usuario usuario = Usuario.builder().nome("Pepe").email("pepe@gmail.com").build();
        repository.save(usuario);
        boolean result = repository.existsByEmail("pepe@gmail.com");

        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoTemEmail(){
        repository.deleteAll();
        boolean result = repository.existsByEmail("usuario@gmail.com");
        Assertions.assertThat(result).isFalse();
    }

}
