package com.example.springreact.Service.Impl;

import com.example.springreact.Service.UsuarioService;
import com.example.springreact.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class) //está no lugar do RunWith pq a versão do SpringBoot é nova e não uso JUnit4
@ActiveProfiles("test")
public class UsuarioServicelTest {
    @Autowired
    UsuarioService service;
    @Autowired
    UsuarioRepository repository;

    @Test // isso não aceita: (expected=Test.None.class)
    public void deveValidarEmail(){
        repository.deleteAll();
        service.validarEmail("email@email.com");
    }


}
